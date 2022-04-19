// Implmentacion sencilla y concurrente del Juego de la Vida
import java.util.Scanner;

public class JuegoVida
{
    // Funcion que genera la siguiente generacion
    static void siguienteGen(int tablero[][], int M, int N, int G)
    {
        // Tablero que guarda los cambios de la siguiente generacion
        int[][] sig = new int[M][N];
 
        // Recorre el tablero para analizar cada celula
        for (int l = 0; l < M; l++)
        {
            for (int m = 0; m < N; m++)
            {
                // Cuenta la cantidad de celulas vivas a su alrededor
                int vecinosVivos = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                      if ((l+i>=0 && l+i<M) && (m+j>=0 && m+j<N))
                        vecinosVivos += tablero[l + i][m + j];
 
                // Se evita que la celula sea contada como vecina
                // Que el programa no confunda la celula a analizar con una vecina
                vecinosVivos -= tablero[l][m];
 
                // Reglas del Juego 
                // Celula viva con menos de dos vecinos MUERE
                if ((tablero[l][m] == 1) && (vecinosVivos < 2))
                   sig[l][m] = 0;
 
                // Celula viva con mas de tres vecinos MUERE
                else if ((tablero[l][m] == 1) && (vecinosVivos > 3))
                    sig[l][m] = 0;
 
                // Celula muerta con tres vecinos NACE
                else if ((tablero[l][m] == 0) && (vecinosVivos == 3))
                    sig[l][m] = 1;
 
                // Se mantiene viva o muerta la celula
                else
                    sig[l][m] = tablero[l][m];
            }
        }
 
        System.out.println("Generacion " + G);
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (sig[i][j] == 0)
                {
                    tablero[i][j] = 0;
                    System.out.print("~");
                }                  
                else
                {
                    tablero[i][j] = 1;
                    System.out.print("#");
                }
                    
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        // Generando el tablero     
        Scanner sc = new Scanner(System.in);

        System.out.println("Vamos a generar tu tablero...");
        System.out.print("Teclea los renglones: ");
        int M = sc.nextInt(); 
        System.out.print("Ahora las columnas: ");
        int N = sc.nextInt();

        int[][] tablero = new int[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                tablero[i][j] = (int)Math.round(Math.random());        
        
        // Muestra tablero original
        System.out.println();
        System.out.println("Simbologia:\nCelulas Vivas -> # \nCelulas Muertas -> ~\n");
        System.out.println("Generacion Original");
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (tablero[i][j] == 0)
                    System.out.print("~");
                else
                    System.out.print("#");
            }
            System.out.println();
        }
        System.out.println();

        // Se repite hasta completar las generaciones
        System.out.print("Numero de generaciones posteriores: ");
        int G = sc.nextInt();
        System.out.println();
        for (int i = 1; i <= G; i++)
            siguienteGen(tablero, M, N, i);
        System.out.println("FIN DEL JUEGO");
    }
}