package Views;

import Controllers.MensajeFijadoController;
import Controllers.SimpleChatController;
import Models.UserModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Miguel Roa
 */
public class SimpleChatView extends javax.swing.JPanel {
        
    SimpleChatController simpleChatController;
    MensajeFijadoController mensajeFijadoController;
    UserModel usuario;
    String chatCompleto;
    Boolean esProfesor;
    Color color;
        
    public SimpleChatView(){
        initComponents();
    }
    
    public SimpleChatView(UserModel usuarioIngresado, Boolean esProfesor, Color color){
        this();
        
        //Inicializamos las variables
        this.usuario = usuarioIngresado;
        this.esProfesor = esProfesor;            
        
        if(color == null || color == Color.WHITE){
            this.color = Color.BLACK;
        }else{
            this.color = color;
        }
        
        //Se setea el color para el mensaje fijado
        this.txtMensajeFijado.setForeground(this.color);
        
        //Se inicializan los controladores
        try{
            simpleChatController = new SimpleChatController(this);           
        }catch(Exception error){
            System.out.println("Existe un error al inicializar el controlador para el chat simple: " + error.toString());
        }        
        
        try{                
            mensajeFijadoController = new MensajeFijadoController(this, esProfesor);
        }catch(Exception error){
            System.out.println("Existe un error inicializar el controlador para el mensaje fijado: " + error.toString());
        }  
                
        //Para que las lineas del chat salten automáticamente
        txtMensaje.setLineWrap(true);        
        txtChat.setLineWrap(true);  
        txtMensajeFijado.setLineWrap(true);
        
        //test
        System.out.println("El usuario ingresado es el siguiente \n"
                + "Nombre: " + usuarioIngresado.getNombre() + "\n"
                + "Apellido: " + usuarioIngresado.getApellido() + "\n"
                + "Color: " + usuarioIngresado.getColor().toString()
        );    
        
        //Si el usuario ingresado no es profesor, se deshabilita el botón de fijado de mensaje
        if(this.esProfesor != null && this.esProfesor == true){
            this.btnMensajeFijado.setEnabled(true);
        }else{
            this.btnMensajeFijado.setEnabled(false);
        }
        
        //IMPORTANTE
        /**
         * Se carga el nuevo mensaje fijado que decidió tener el profesor para la sesión activa o pasada
        */
        this.simpleChatController.leerMensajeFijado();                
    }
    
    //Set y get
    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void setBtnEnviar(JButton btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JTextArea getTxtChat() {
        return txtChat;
    }

    public void setTxtChat(JTextArea txtChat) {
        this.txtChat = txtChat;
    }

    public JTextArea getTxtMensaje() {
        return txtMensaje;
    }

    public void setTxtMensaje(JTextArea txtMensaje) {
        this.txtMensaje = txtMensaje;
    }

    public String getChatCompleto() {
        return chatCompleto;
    }

    public void setChatCompleto(String chatCompleto) {
        this.chatCompleto = chatCompleto;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public JTextArea getTxtMensajeFijado() {
        return txtMensajeFijado;
    }

    public void setTxtMensajeFijado(JTextArea txtMensajeFijado) {
        this.txtMensajeFijado = txtMensajeFijado;
    }    
    
               
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        btnMensajeFijado = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMensajeFijado = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Chat simple"));
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(1100, 900));
        setMinimumSize(new java.awt.Dimension(550, 450));
        setPreferredSize(new java.awt.Dimension(550, 450));
        setRequestFocusEnabled(false);

        btnEnviar.setBackground(new java.awt.Color(122, 145, 255));
        btnEnviar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMensaje.setRows(5);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMensajeKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtMensaje);

        btnMensajeFijado.setBackground(new java.awt.Color(122, 145, 255));
        btnMensajeFijado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnMensajeFijado.setForeground(new java.awt.Color(255, 255, 255));
        btnMensajeFijado.setText("Fijar");
        btnMensajeFijado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajeFijadoActionPerformed(evt);
            }
        });

        txtMensajeFijado.setEditable(false);
        txtMensajeFijado.setColumns(20);
        txtMensajeFijado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMensajeFijado.setForeground(new java.awt.Color(153, 0, 51));
        txtMensajeFijado.setRows(5);
        txtMensajeFijado.setBorder(null);
        jScrollPane3.setViewportView(txtMensajeFijado);

        jLabel1.setText("Mensaje Fijado por el profesor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(btnMensajeFijado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMensajeFijado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(3, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEnviar, btnMensajeFijado});

    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try{
            simpleChatController.enviarMensaje();            
        }catch(Exception error){
            System.out.println("Existe un error al querer enviar el mensaje en cuestion" + error);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtMensajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
                simpleChatController.enviarMensaje();            
            }catch(Exception error){
                System.out.println("Existe un error para enviar el mensaje en cuestión: " + error.toString());
            }           
        }
    }//GEN-LAST:event_txtMensajeKeyReleased

    private void btnMensajeFijadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajeFijadoActionPerformed
        try{
            mensajeFijadoController.enviarMensaje();
        }catch(Exception error){
            System.out.println("Existe un error para fijar el mensaje: " + error.toString());
        }
    }//GEN-LAST:event_btnMensajeFijadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnMensajeFijado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextArea txtMensajeFijado;
    // End of variables declaration//GEN-END:variables
}
