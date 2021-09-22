package almacennook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class AlmacenNook {
    
   
    Box Stuff[]=new Box[19];
    double TOT= 0;
    int pr= 10,ne=10, fc=1, one=0, two=0, three=0, all=0;
    String lst="", Fact="";
    
private void Default(){
    for(int i=0;i<Stuff.length;i++){
       Stuff[i]= new Box("",0, "", 0, "" );
    }
}

private void List()throws IOException , FileNotFoundException {
    File file = new File("Almacen.txt");
        Scanner scanner;
       lst="";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
               
                String linea = scanner.nextLine();
                
                lst+=linea+"\n";
               
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    one=0;two=0;three=0;all=0;TOT=0;
    for(int i=0, j=1;i<pr;i++,j++){
        
        int pos=lst.toLowerCase().indexOf("producto "+j );
        
        if(pos>0){
        int tos=lst.toLowerCase().indexOf("nombre:", pos);
        
        int cos=lst.toLowerCase().indexOf("#", tos+7);
        String nm= lst.substring(tos+7, cos);
        tos=lst.toLowerCase().indexOf("precio:", pos);
        cos=lst.toLowerCase().indexOf("#", tos+7);
        String pz= lst.substring(tos+7, cos);
        
        pz=pz.trim();
        double prz= Double.parseDouble(pz);
        
        tos=lst.toLowerCase().indexOf("descripción:", pos);
        cos=lst.toLowerCase().indexOf("#", tos+12);
        String ds= lst.substring(tos+12, cos);
        
        tos=lst.toLowerCase().indexOf("cantidad:", pos);
        cos=lst.toLowerCase().indexOf("#", tos+9);
        String can= lst.substring(tos+9, cos);
        can=can.trim();
        String c= "";
        int cant=0;

        for(int k=0;!c.equals(can);k++){
            c= "";
            c+=k;
            if(c.equals(can)){
                cant= k;
            }
        }
        
        tos=lst.toLowerCase().indexOf("almacén:", pos);
        cos=lst.toLowerCase().indexOf("#", tos+8);
        String al= lst.substring(tos+8, cos);
        al=al.trim();
       
        if("1".equals(al)){ one+=cant;} else if("2".equals(al)){two+=cant;} else three+=cant;
        TOT+= cant*prz;
        
        Stuff[i]=new Box(nm,prz,ds,cant,al);
        
        if(i==pr-1){j++;pos=lst.toLowerCase().indexOf("producto "+j );
        if(pos>0){pr++; j--;}}
        }
        
    }
    all= one + two + three;
 
}

private void Check(){
     Scanner sc=new Scanner(System.in);
     int a;
     do{
    System.out.println("Seleccione un artículo");
               a= sc.nextInt();
                if(a>pr-1 || a<0){System.err.println("Opción inválida");}
                else{
             for(int i=0;i<5;i++){System.out.print("\n");}
             System.out.println("\t\t\t"+Stuff[a].name+"\n"+Stuff[a].Des + "\n\nPrecio:"
             + Stuff[a].$ + "$ \nCantidad:" + Stuff[a].cant + "\nAlmacen:" + Stuff[a].alm );}
                }while(a>pr-1 || a<0);
     System.out.print("Ingrese cualquier tecla para continuar:"); String que=sc.next();
}

private void Change() throws IOException{
    Scanner sc=new Scanner(System.in);
     int a;
     do{
    System.out.print("Seleccione el artículo que va a modificar:");
               a= sc.nextInt();
                if(a>pr-1 || a<0){System.err.println("Opción inválida");}
                
    }while(a>pr-1 || a<0);
    
    System.out.println("Modificar información específica (1) o completa (2)");
    int p= sc.nextInt();
    a++;
    int pos, cos, tos;
    
    switch(p){
        case 1:
            ChangeOnlyone(a);
            break;
        case 2:
            pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("nombre:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+7);
                
                System.out.print("Ingrese el nuevo nombre:");
                sc.nextLine();
                String nm=sc.nextLine();
                lst=lst.replace(lst.substring(tos+7, cos), nm);
                
                tos=lst.toLowerCase().indexOf("descripción:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+12);
                
                System.out.print("Ingrese la nueva descripción:");
                
                String ds=sc.nextLine();
                lst=lst.replace(lst.substring(tos+12, cos), ds);
                
                tos=lst.toLowerCase().indexOf("precio:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+7);
                
                System.out.print("Ingrese el nuevo precio:");
                
                String prz=sc.nextLine();
                lst=lst.replace(lst.substring(tos+7, cos), prz);
                
                tos=lst.toLowerCase().indexOf("cantidad:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+9);
                
                System.out.print("Ingrese la nueva cantidad:");
                
                String cant=sc.next();
                lst=lst.replace(lst.substring(tos+9, cos), cant);
                
                tos=lst.toLowerCase().indexOf("almacén:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+8);
                
                String alm;
             
             System.out.print("Ingrese el almacén:");
              alm= sc.next(); 
              
                lst=lst.replace(lst.substring(tos+8, cos), alm);
                
            break;
            
            default:
                System.err.println("Opción inválida");
                break;
        
        //fin switch
    }
    
    Update kachaw= new Update();
    kachaw.Modify(lst);
    List();
}

private void ChangeOnlyone(int a){
    Scanner sc=new Scanner(System.in);
    boolean txt=true;
    do{
    System.out.println("\t\t Qué desea editar?");
            System.out.println("[1] Nombre.");
            System.out.println("[2] Precio.");
            System.out.println("[3] Descripción.");
            System.out.println("[4] Cantidad.");
            System.out.println("[5] Almacen.");
        int q= sc.nextInt();
        int pos, cos, tos;
    
        switch(q){
            case 1:
                pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("nombre:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+7);
                
                System.out.print("Ingrese el nuevo nombre:");
                sc.nextLine();
                String nm=sc.nextLine();
                lst=lst.replace(lst.substring(tos+7, cos), nm);
                
                break;
                
            case 2:
                pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("precio:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+7);
                
                System.out.print("Ingrese el nuevo precio:");
                sc.nextLine();
                String prz=sc.nextLine();
                lst=lst.replace(lst.substring(tos+7, cos), prz);
                break;
                
            case 3:
                pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("descripción:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+12);
                
                System.out.print("Ingrese la nueva descripción:");
                sc.nextLine();
                String ds=sc.nextLine();
                lst=lst.replace(lst.substring(tos+12, cos), ds);
                break;
                
            case 4:
                pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("cantidad:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+9);
                
                System.out.print("Ingrese la nueva cantidad:");
                sc.nextLine();
                String cant=sc.next();
                lst=lst.replace(lst.substring(tos+9, cos), cant);
                break;
                
            case 5:
                pos=lst.toLowerCase().indexOf("producto "+a );
                tos=lst.toLowerCase().indexOf("almacén:", pos);
        
                cos=lst.toLowerCase().indexOf("#", tos+8);
                
                String alm;
              
             System.out.print("Ingrese el almacén:");
              alm= sc.next(); 
              
                lst=lst.replace(lst.substring(tos+8, cos), alm);
                break;
                
            default:
                System.err.println("Opción inválida");
                break;
            //fin switch
        }
    System.out.println("Editar algo más? (Si=1,No=0)");
    int y=sc.nextInt();
    
    if(y==1){txt=false;}else{txt=true;}
    
    }while(txt==false);
}

private void Mas() throws IOException{
     Scanner sc=new Scanner(System.in);
     
    System.out.print("Ingrese el nombre:");
              String a= sc.nextLine();
    System.out.print("Ingrese la descripción:");
              String b= sc.nextLine();
    System.out.print("Ingrese el precio:");
              String c= sc.next();   
    System.out.print("Ingrese la cantidad:");
              String d= sc.next();  
              
              String e;
              
    System.out.print("Ingrese el almacén:");
              e= sc.next(); 
              
              Update kachaw= new Update();
              pr++;
              kachaw.Add(a, c, b, d, e, lst,pr);
              
              List();
}

private void kill() throws IOException{
    Scanner sc=new Scanner(System.in);
     int a=0;
     do{
    System.out.print("Seleccione el artículo que va a Eliminar:");
               a= sc.nextInt();
                if(a>pr-1 || a<0){System.err.println("Opción inválida");}
                
    }while(a>pr-1 || a<0);
     a++;
     
    int pos=lst.toLowerCase().indexOf("producto "+a );
    
    int tos=lst.toLowerCase().indexOf("almacén:", pos); 
    int cos=lst.toLowerCase().indexOf("#", tos+8);
    lst=lst.replace(lst.substring(pos, cos+1), "");
    
    
    String e;
    for(int i=0, j=a+1;i<pr;i++,j++){
        e="";
       
        e+=a;
        
        pos=lst.toLowerCase().indexOf("producto "+j );
        
        if(pos>0){
        cos=lst.toLowerCase().indexOf("#", pos);
        lst=lst.replace(lst.substring(pos+8, cos)," "+e );
        a++;
        }
    
    Stuff[pr-1]= new Box("",0,"", 0, "" );}
    pr--;
    Update kachaw= new Update();
    kachaw.Modify(lst);
    
    List();
    
    
}
    
public void Items() throws IOException{
    Scanner sc=new Scanner(System.in);
    boolean txt= false;    
    do{
    System.out.println("\t\tLista de Productos");
    for(int i=0;i<Stuff.length;i++){
        if(Stuff[i].cant==0){break;}
        System.out.println("["+i+"]"+Stuff[i].name);
    }
        
    for(int i=0;i<1;i++){System.out.print("\n");}
    System.out.println("Seleccione una opción");
    System.out.println("1.Información de producto");
    System.out.println("2.Agregar producto");
    System.out.println("3.Modificar producto");
    System.out.println("4.Eliminar producto");
    System.out.println("5.Salir");
    System.out.print("Opción:");
    int p=sc.nextInt();
        
       switch(p){
           case 1:
               Check();
             break;
             
           case 2:
               Mas();
            break;
           
           case 3:
               Change();
               break;
           
           case 4:
               kill();
               break;
               
           case 5:
               txt=true;
               break;
           
           default:
               System.err.println("Opción inválida");
               break;
           //Fin switch
       }
    }while(txt==false);
    }

private void Sta(){
    Scanner sc=new Scanner(System.in);
    for(int i=0;i<1;i++){System.out.print("\n");}
    System.out.println("Seleccione una opción");
    System.out.println("1.Cantidad de productos por almacén");
    System.out.println("2.Cantidad de productos en general");
    System.out.println("3.Dinero total en el almacén");
    System.out.print("Opción:");
    int p=sc.nextInt();
    
    switch(p){
        case 1:
            System.out.println("Actualmente se encuentran ["+one+"] productos en el"
                    + " almacén 1... \n["+two+"] en el almacén 2 y ["+three+"] en el"
                            + " almacén 3");
        break;
        
        case 2:
            System.out.println("En total hay ["+all+"] productos");
        
        break;
        
        case 3:
            System.out.println("Actualmente hay ["+TOT+"$] en el almacén");
            
            break;
            
        default:
            System.err.println("Opción inválida");
            break;
        //fin switch
    }
    System.out.print("Ingrese cualquier tecla para continuar:"); String que=sc.next();
}

private void shopping() throws IOException{
    Scanner sc=new Scanner(System.in);
    int a,b,c=0;
    double tot=0,IVA=0,bs=0;
    boolean txt= false;    
    do{
    System.out.println("\t\tLista de Productos");
    for(int i=0;i<Stuff.length;i++){
        if(Stuff[i].cant==0){break;}
        System.out.println("["+i+"]"+Stuff[i].name+"(cant:"+Stuff[i].cant+")");
    }
    do{
    System.out.print("Qué Artículo va a llevar?:");
    a=sc.nextInt();
    
    if(a>pr-1 || a<0){System.err.println("Opción inválida");}
                else{
             for(int i=0;i<5;i++){System.out.print("\n");}
             System.out.println("\t\t\t"+Stuff[a].name+"\n"+Stuff[a].Des + "\n\nPrecio:"
             + Stuff[a].$ + "$ \nCantidad:" + Stuff[a].cant + "\nAlmacen:" + Stuff[a].alm );}
                }while(a>pr-1 || a<0);
    
    do{
     System.out.print("Cuantos va a llevar?:"); 
     b=sc.nextInt();
     if(Stuff[a].cant<b || b<0){System.err.println("Cantidad inválida");}
     
    }while(Stuff[a].cant<b || b<0);
    
    
    
    tot+= b*Stuff[a].$;
    c+=b;
    int r=Stuff[a].cant-b;
    String h=" "+r;
    a++;
    int pos=lst.toLowerCase().indexOf("producto "+a );
    int tos=lst.toLowerCase().indexOf("cantidad:", pos); 
    int cos=lst.toLowerCase().indexOf("#", tos+9);
    lst=lst.replace(lst.substring(tos+9, cos), h);
    
    System.out.println("Desea Algo más? (1=Si, 2=No)");
                 int give=sc.nextInt();
                 if(give==1){txt=true;}else{txt=false;}
    }while(txt==true);
    
    IVA=tot + (tot*0.16);
    bs=IVA* 75000;     
    Fact+="Factura "+ fc;
    
    
    Update kachaw= new Update();
    
    String can=""+c,sub=""+tot,total=""+IVA,bss=""+bs;
    kachaw.Factura(can, sub, total, bss,fc);
                 kachaw.Modify(lst);
                 List();
                 fc++;
    System.out.print("Ingrese cualquier tecla para continuar:"); String cant=sc.next();     
}

private void Fact(){
    Scanner sc=new Scanner(System.in);
    int pos;String ff;
    
    System.out.print("Ingrese nombre de la factura(Ej: Factura 1):"); 
    ff=sc.nextLine();
    ff=ff.toLowerCase();
    pos=Fact.toLowerCase().indexOf(ff);
    if(pos<0){System.err.println("No existe la factura");}
    else{
    Update kachaw= new Update();
    kachaw.ReadFactura(ff);
     System.out.print("Ingrese cualquier tecla para continuar:"); String cant=sc.next();}
}

private void Menu() throws IOException{
    Scanner sc=new Scanner(System.in);
    boolean txt=true;
    do{
    for(int i=0;i<1;i++){System.out.print("\n");}
    System.out.println("\t\tMenú");
    System.out.println("1.Lista de producto");
    System.out.println("2.Procesar compra");
    System.out.println("3.Ver factura");
    System.out.println("4.Estadísticas");
    System.out.println("5.Salir del sistema");
    System.out.print("Opción:");
    int p=sc.nextInt();
    
    switch(p){
        case 1:
            Items();
            break;
        case 2:
            shopping();
            break;
        case 3:
            Fact();
            break;
        case 4:
            Sta();
            break;
        case 5:
            txt=false;
            break;
        default:
            System.err.println("Opción inválida");
            break;
        
        
        //fin switch
    }
    }while(txt==true);
}








    public static void main(String[] args) throws IOException {
        AlmacenNook go = new AlmacenNook();
        go.Default();
        go.List();
        go.Menu();
        
    }
    
}
