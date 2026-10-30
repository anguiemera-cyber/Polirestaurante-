package co.edu.poligran.paradigmas.polirestaurante.formularios;

public class FormularioPersona extends javax.swing.JFrame {
    public FormularioPersona() { initComponents(); setLocationRelativeTo(null); }
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        panelFormulario = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        lbl0 = new javax.swing.JLabel();
        txt0 = new javax.swing.JTextField();
        lbl0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl0.setText("ID:");
        panelFormulario.add(lbl0);
        txt0.setColumns(22);
        panelFormulario.add(txt0);
        lbl1 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl1.setText("Nombre:");
        panelFormulario.add(lbl1);
        panelFormulario.setLayout(new java.awt.GridLayout(0, 2, 8, 8));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 6));
        txt1.setColumns(22);
        panelFormulario.add(txt1);
        btnGuardar = new javax.swing.JButton();
        btnGuardar.setText("Guardar");
        panelBotones.add(btnGuardar);
        btnActualizar = new javax.swing.JButton();
        btnActualizar.setText("Actualizar");
        panelBotones.add(btnActualizar);
        btnEliminar = new javax.swing.JButton();
        btnEliminar.setText("Eliminar");
        panelBotones.add(btnEliminar);
        btnLimpiar = new javax.swing.JButton();
        btnLimpiar.setText("Limpiar");
        panelBotones.add(btnLimpiar);
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setText("Buscar");
        panelBotones.add(btnBuscar);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Persona");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }
    // </editor-fold>
    public static void main(String args[]) { java.awt.EventQueue.invokeLater(() -> new FormularioPersona().setVisible(true)); }
    // Variables declaration - do not modify
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lbl0;
    private javax.swing.JTextField txt0;
    private javax.swing.JLabel lbl1;
    private javax.swing.JTextField txt1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}
