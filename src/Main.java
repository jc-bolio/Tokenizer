import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        System.out.println(new File(".").getAbsoluteFile());

        FileReader reader = new FileReader("TestCode.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StreamTokenizer token = new StreamTokenizer(buffer);

        boolean arg = true;
        token.slashStarComments(true);

        int t;
        while ((t = token.nextToken()) != StreamTokenizer.TT_EOF)
        {
            switch (t)
            {
                case StreamTokenizer.TT_WORD:
                    System.out.println("Word : " + token.sval);
                    break;
                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Number : " + token.nval);
                    break;
            }
        }
    }
}
