package almacennook;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Update {
    
    
    public void Add(String name, String prz, String ds, String cant, 
            String alm, String lst, int n)throws IOException , FileNotFoundException{
        
        
        String nuevo="\nProducto "+n+" #\n\n"+"Nombre: "+name+" #"
                +"\nPrecio: "+prz+" #"+"\nDescripción: "+ds+" #"+"\nCantidad: "+cant
                +" #"+"\nAlmacén: "+alm+" #";
        nuevo=lst+nuevo;
        
        String ruta = "Almacen.txt";
        File archivo = new File(ruta);
        
        
        BufferedWriter bw;
        
        
        if(archivo.exists()) {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = nuevo.split("#");
        

        for (String arrToShow : arr) {
            arrToShow=arrToShow+ " #";
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
           
        } else {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = nuevo.split("#");
        

        for (String arrToShow : arr) {
            arrToShow=arrToShow+ " #";
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
            
         
        }
        bw.close();
        
    
        
    }
    
    public void Modify(String lista)throws IOException , FileNotFoundException{
        String ruta = "Almacen.txt";
        File archivo = new File(ruta);
        
        
        BufferedWriter bw;
        
        
        if(archivo.exists()) {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = lista.split("#");
        
        for (String arrToShow : arr) {
            arrToShow=arrToShow+ " #";
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
           
        } else {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = lista.split("#");
        

        for (String arrToShow : arr) {
            arrToShow=arrToShow+ " #";
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
            
        }
        bw.close();
        
    }
    
    public void Factura(String a, String b, String c, String d,int fct)throws IOException , FileNotFoundException{
        
        String nuevo="\t\tAlmacén Nook "+" #\n\n"+"Cantidad de productos: "+a+" #"
                +"\n\nSubtotal: "+b+"$ #"+"\n\nTotal(IVA 16%): "+c+"$ #"+"\n\nBsS: "+d
                +"BsS #";
        
        String ruta = "factura "+fct+".txt";
        File archivo = new File(ruta);
        String ff="factura "+fct;
        
        
        BufferedWriter bw;
        
        
        if(archivo.exists()) {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = nuevo.split("#");
        

        for (String arrToShow : arr) {
            
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
           
        } else {
            
            bw = new BufferedWriter(new FileWriter(archivo));
            
            String[] arr = nuevo.split("#");
        

        for (String arrToShow : arr) {
            
            bw.write(arrToShow);
            bw.newLine();
            bw.newLine();
        }
            
         
        }
        bw.close();
        ReadFactura(ff);
        
    }
   
    
    public void ReadFactura(String a){
        
        File file = new File(a+".txt");
        Scanner scanner;
       String fc="";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
               
                String linea = scanner.nextLine();
                
                fc+=linea+"\n";
               
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(fc);
    }
    
}
