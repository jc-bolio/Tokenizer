import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        FileReader reader = new FileReader("TestCode.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StreamTokenizer token = new StreamTokenizer(buffer);

        Table table = new Table();

        boolean ignorarComentarios = true;
        token.slashStarComments(ignorarComentarios);
        token.slashSlashComments(ignorarComentarios);

        int t;
        while ((t = token.nextToken()) != StreamTokenizer.TT_EOF) {

            switch (t) {
                case StreamTokenizer.TT_WORD:

                    switch (token.sval){
                        case "class":
                        case "boolean":
                        case "byte":
                        case "char":
                        case "float":
                        case "double":
                        case "int":
                        case "String":
                            System.out.println("Linea " + token.lineno() + ": declaración de " + token.sval);
                            table.addToTable(token.sval, "declaration");
                            break;

                        default:
                            System.out.println("Linea " + token.lineno() + ": id : " + token.sval);
                            break;
                    }

                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Linea " + token.lineno() + ": núm : " + token.nval);
                    break;

                case '+':
                    break;

                case '-':
                    break;

                case '/':
                    break;

                case '*':
                    break;

                case '<':
                {
                    int tkn = token.nextToken();
                    switch(tkn) {
                        case '=':
                            System.out.println("<=");
                            break;
                        case '<':
                            System.out.println("<<");
                            break;
                        default:
                            token.pushBack();
                            System.out.println("<");
                            break;
                    }
                }
            }
        }

        table.printTable();
    }
}
