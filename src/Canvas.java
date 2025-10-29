import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private final static int STROKE_SIZE = 8;

    // menyimpan seluruh garis dan titik
    private List<List<ColorPoint>> allPaths;

    // untuk menggambar garis antar titik
    private List<ColorPoint> currentPath;

    // warna titik
    private Color color;

    // panjang dan lebar kanvas
    private int canvasWidth, canvasHeight;

    // lokasi titik
    private int x, y;

    public Canvas(int targetWidth, int targetHeight){
        super();
        setPreferredSize(new Dimension(targetWidth, targetHeight));
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // init vars
        allPaths = new ArrayList<>(25);
        canvasWidth = targetWidth;
        canvasHeight = targetHeight;

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ambil lokasi mouse saat ini
                x = e.getX();
                y = e.getY();

                // gambar di lokasi mouse
                Graphics g = getGraphics();
                g.setColor(color);
                g.fillRect(x, y, STROKE_SIZE, STROKE_SIZE);
                g.dispose();

                // mulai dari path saat ini
                currentPath = new ArrayList<>(25);
                currentPath.add(new ColorPoint(x, y, color));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // tambah path atau jalur
                allPaths.add(currentPath);

               // reset path saat ini
                currentPath = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // ambil lokasi saat ini
                x = e.getX();
                y = e.getY();

                // untuk menggambar garis
                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setColor(color);
                if(!currentPath.isEmpty()){
                    ColorPoint prevPoint = currentPath.get(currentPath.size() - 1);
                    g2d.setStroke(new BasicStroke(STROKE_SIZE));

                    // koneksikan garis antar titik
                    g2d.drawLine(prevPoint.getX(), prevPoint.getY(), x, y);
                }
                g2d.dispose();

                // nambah point baru ke path
                ColorPoint nextPoint = new ColorPoint(e.getX(), e.getY(), color);
                currentPath.add(nextPoint);            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void resetCanvas(){
        // hapus seluruh yang ada di canvas
        Graphics g = getGraphics();
        g.clearRect(0, 0, canvasWidth, canvasHeight);
        g.dispose();

        // reset path
        allPaths = new ArrayList<>(25);
        currentPath = null;

        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // gambar ulang jalur
        for(List<ColorPoint> path : allPaths){
            ColorPoint from = null;
            for(ColorPoint point : path){
                g2d.setColor(point.getColor());

                // edge case when a single dot is created
                if(path.size() == 1){
                    g2d.fillRect(point.getX(), point.getY(), STROKE_SIZE, STROKE_SIZE);
                }

                if(from != null){
                    g2d.setStroke(new BasicStroke(STROKE_SIZE));
                    g2d.drawLine(from.getX(), from.getY(), point.getX(), point.getY());
                }
                from = point;
            }
        }
    }
}
