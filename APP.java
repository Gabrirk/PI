import javax.swing.SwingUtilities;

public class APP {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mostrarGUI();
            }
        });
    }

    private static void mostrarGUI() {
        AppFrame frame = new AppFrame();
        frame.mostrar();
    }


} // fim da classe APP