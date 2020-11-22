import java.io.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.filechooser.*; 
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

class Gui extends JFrame implements ActionListener { 
  
    static JLabel l; 
    JFileChooser j ;
  
    public static void main(String args[]) 
    { 
      
        JFrame f = new JFrame("Image Compressor"); 
  
  
        f.setSize(400, 400); 
  

        f.setVisible(true); 
  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  

        JButton button1 = new JButton("Choose"); 
  

        JButton button2 = new JButton("Compress"); 
  

        Gui g1 = new Gui(); 
  

        button1.addActionListener(g1); 
        button2.addActionListener(g1); 
  

        JPanel p = new JPanel(); 
  

        p.add(button1); 
        p.add(button2); 
 
        l = new JLabel("no file selected"); 
  
  
        p.add(l); 
        f.add(p); 
  
        f.show(); 
    } 
    public void actionPerformed(ActionEvent evt) 
    { 

        String com = evt.getActionCommand(); 
  
        if (com.equals("Choose")) { 

        	j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
        
            int r = j.showOpenDialog(null); 
  
          
            if (r == JFileChooser.APPROVE_OPTION) 
  
            { 
            
                l.setText(j.getSelectedFile().getAbsolutePath()); 
            } 
        
            else
                l.setText("the user cancelled the operation"); 
 
        
        } 
  
   
        else { 

      try{  	

      File input = new File(j.getSelectedFile().getAbsolutePath());
      BufferedImage image = ImageIO.read(input);

      File compressedImageFile = new File("compress.jpg");
      OutputStream os =new FileOutputStream(compressedImageFile);

      Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter writer = (ImageWriter) writers.next();

      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      ImageWriteParam param = writer.getDefaultWriteParam();
      
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(0.5f);                                              // pass quality factor through variable.
      writer.write(null, new IIOImage(image, null, null), param);
      
      os.close();
      ios.close();
      writer.dispose();
       l.setText("Compression completed"); 


       }
      catch(IOException e ){
      	System.out.println("Error occured");
      }


       
          
        } 
    } 
} 
