package co.edu.poligran.paradigmas.polirestaurante.formularios;

public class FormularioCocinero extends javax.swing.JFrame {
    public FormularioCocinero() { initComponents(); setLocationRelativeTo(null); }
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
        txt1.setColumns(22);
        panelFormulario.add(txt1);
        lbl2 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl2.setText("Apellido:");
        panelFormulario.add(lbl2);
        txt2.setColumns(22);
        panelFormulario.add(txt2);
        lbl3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl3.setText("Tipo de documento:");
        panelFormulario.add(lbl3);
        txt3.setColumns(22);
        panelFormulario.add(txt3);
        lbl4 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl4.setText("Número de documento:");
        panelFormulario.add(lbl4);
        txt4.setColumns(22);
        panelFormulario.add(txt4);
        lbl5 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl5.setText("Celular:");
        panelFormulario.add(lbl5);
        txt5.setColumns(22);
        panelFormulario.add(txt5);
        lbl6 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl6.setText("Correo:");
        panelFormulario.add(lbl6);
        txt6.setColumns(22);
        panelFormulario.add(txt6);
        lbl7 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl7.setText("Usuario:");
        panelFormulario.add(lbl7);
        panelFormulario.setLayout(new java.awt.GridLayout(0, 2, 8, 8));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 6));
        txt7.setColumns(22);
        panelFormulario.add(txt7);
        lbl8 = new javax.swing.JLabel();
        txt8 = new javax.swing.JTextField();
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl8.setText("Contraseña:");
        panelFormulario.add(lbl8);
        txt8.setColumns(22);
        panelFormulario.add(txt8);
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
        setTitle("Registro de Cocinero");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }
    // </editor-fold>
    public static void main(String args[]) { java.awt.EventQueue.invokeLater(() -> new FormularioCocinero().setVisible(true)); }
    // Variables declaration - do not modify
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lbl0;
    private javax.swing.JTextField txt0;
    private javax.swing.JLabel lbl1;
    private javax.swing.JTextField txt1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JTextField txt2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JTextField txt3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JTextField txt4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JTextField txt5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JTextField txt6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JTextField txt7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JTextField txt8;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}
