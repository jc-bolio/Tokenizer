import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        FileReader reader = new FileReader("TestCode.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StreamTokenizer token = new StreamTokenizer(buffer);

        boolean ignorarComentarios = true;
        token.slashStarComments(ignorarComentarios);
        token.slashSlashComments(ignorarComentarios);

        int t;
        while ((t = token.nextToken()) != StreamTokenizer.TT_EOF)
        {
            switch (t)
            {
                case StreamTokenizer.TT_WORD:

                    System.out.println("Linea " + token.lineno() + ": id : " + token.sval);
                    break;
                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Linea " + token.lineno() + ": núm : " + token.nval);
                    break;
            }
        }
    }
}
