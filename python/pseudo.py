import fitz  # PyMuPDF
from pypdf import PdfReader, PdfWriter
from pathlib import Path

def is_marker(pix, box_size=200, threshold=60, black_ratio=0.05):
    """
    Erkennt Marker im linken oberen Eck eines Pixmap-Bildes.
    - box_size: Größe des untersuchten Quadrats in Pixeln
    - threshold: alles darunter gilt als "schwarz"
    - black_ratio: Anteil schwarzer Pixel, der erreicht werden muss
    """
    w, h = pix.width, pix.height
    img = pix.samples
    n = pix.n  # Kanäle (RGB=3, RGBA=4)

    black_pixels = 0
    total = 0

    for y in range(0, min(box_size, h)):
        for x in range(0, min(box_size, w)):
            offset = (y * w + x) * n
            gray = sum(img[offset:offset+3]) / 3
            if gray < threshold:
                black_pixels += 1
            total += 1

    ratio = black_pixels / total
    return ratio > black_ratio, ratio


def split_pdf_by_marker(input_pdf, output_folder="output"):
    """
    Zerlegt ein PDF in mehrere Teile.
    Jeder schwarze Marker oben links startet einen neuen Abschnitt.
    """

    # PyMuPDF zum Analysieren
    doc_fitz = fitz.open(input_pdf)
    print(f"[INFO] PDF geladen: {input_pdf} ({len(doc_fitz)} Seiten)")

    marker_pages = []

    # Jede Seite prüfen
    for i in range(len(doc_fitz)):
        page = doc_fitz[i]
        pix = page.get_pixmap(matrix=fitz.Matrix(3, 3))  # kleine Auflösung
        marker, ratio = is_marker(pix, box_size=200, threshold=60, black_ratio=0.05)

        if marker:
            marker_pages.append(i)
            print(f"[MARKER] erkannt auf Seite {i+1} ({ratio*100:.1f}% schwarze Pixel)")

    # Falls kein Marker gefunden → alles als eine Datei speichern
    if not marker_pages:
        print("[WARNUNG] Keine Marker gefunden, es wird nur eine Datei erstellt.")
        marker_pages = [0]

    marker_pages.append(len(doc_fitz))  # Ende markieren

    # pypdf für Seitenspeicherung
    reader = PdfReader(input_pdf)

    # Ausgabeordner vorbereiten
    out_dir = Path(output_folder)
    out_dir.mkdir(parents=True, exist_ok=True)

    # Blöcke speichern
    for idx in range(len(marker_pages)-1):
        start, end = marker_pages[idx], marker_pages[idx+1]
        writer = PdfWriter()
        for p in range(start, end):
            writer.add_page(reader.pages[p])
        out_path = out_dir / f"Arztbrief_{idx+1}.pdf"
        with out_path.open("wb") as f:
            writer.write(f)
        print(f"[EXPORT] {out_path} (Seiten {start+1}–{end})")

    print("[FERTIG] Alle Arztbriefe gespeichert.")


if __name__ == "__main__":
    split_pdf_by_marker("BRW5CF370D02154_09202025_102244_012829.pdf")
