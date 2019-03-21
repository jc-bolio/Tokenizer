import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        FileReader reader = new FileReader("TestCode.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StreamTokenizer token = new StreamTokenizer(buffer);

        Table table = new Table();

        boolean ignoreComments = true;
        token.slashStarComments(ignoreComments);
        token.slashSlashComments(ignoreComments);

        int t;
        while ((t = token.nextToken()) != StreamTokenizer.TT_EOF) {

            switch (t) {
                case StreamTokenizer.TT_WORD:

                    switch (token.sval){
                        case "void":
                        case "class":
                        case "boolean":
                        case "byte":
                        case "char":
                        case "float":
                        case "double":
                        case "int":
                        case "String":
                            System.out.println("Linea " + token.lineno() + ": declaración: " + token.sval);
                            table.addToTable(token.sval, "declaration");
                            break;

                        default:
                            token.pushBack();
                            if(!token.sval.equals("public")&&!token.sval.equals("private")&&!token.sval.equals("static")){
                                token.nextToken();
                                System.out.println("Linea " + token.lineno() + ": id : " + token.sval);
                                table.addToTable(token.sval, "id");
                            }
                            token.nextToken();
                            break;
                    } break;

                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Linea " + token.lineno() + ": núm : " + token.nval);
                    break;

                case '+':
                case '-':
                case '/':
                case '*':
                    System.out.println("Linea " + token.lineno() + ": operador : " + token.sval);
                    table.addToTable(token.sval, "op");
                    break;

                case '<': {
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
