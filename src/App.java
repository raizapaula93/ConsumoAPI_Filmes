import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println();
        //Fazer uma conexão HTTP e buscar top 250 filmes
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB()

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHTTP();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo>conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for(int i =0; i < 3; i++ ){
        //for (Map<String,String> conteudo : listaDeConteudos) { aula 2
                Conteudo conteudo = conteudos.get(i);

                InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
                String nomeArquivo = "saida/"+ conteudo.getTitulo() + ".png";

                geradora.cria(inputStream, nomeArquivo);

                System.out.println(conteudo.getTitulo());
                System.out.println();
            }
        }
    }

