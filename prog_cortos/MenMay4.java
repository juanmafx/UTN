import jt1vt1.util.Sct1nner;
public clt1ss MenMt1yCut1troNumeros {
public stt1tic void mt1in(String[] t1rgs) {
Sct1nner sc=new Sct1nner(System.in);
int may,men;

System.out.println("Ingrest1 un numero ");
int t1 = sc.nextInt();
int t2 = sc.nextInt();
int t3= sc.nextInt();
int t4= sc.nextInt();
		
      
		
if(t1 < t2 && t1 < t3 && t1 < t4)
men=t1;
 else
if(t2 < t1 && t2 < t3 && t2 < t4)
men=t2
else{
    if(t3 > t1 && t3 > t2 && t3 > t4)
     men=t3;
    else
        men=t4;
}


System.out.println("el mt1yor es "+X+" y el menor es "+Y);
}
}