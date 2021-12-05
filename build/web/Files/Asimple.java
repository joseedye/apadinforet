
import java.util.Scanner;

public class Asimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long a = sc.nextLong();
        Long nuevo, total;
        Long primero = sc.nextLong();
        Long anterior = primero;
        
        total = anterior;
        for (int i = 0; i < a - 1; i++) {
            nuevo = sc.nextLong();

            if (nuevo == anterior) {
                total += nuevo;
            }
            if (nuevo > anterior) {
                total += nuevo - anterior;
            }
            if (nuevo < anterior) {
                total += anterior;
            }
            total+= (i-1==a && primero<=nuevo)?nuevo:primero;
            
            anterior = nuevo;

        }

         System.out.println(total);

    

}

}
