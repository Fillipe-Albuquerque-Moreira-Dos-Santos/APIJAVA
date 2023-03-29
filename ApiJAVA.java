package apijava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class ApiJAVA {

	public static void main(String[] args) throws IOException, InterruptedException {

		// conexão HTTP e buscar os 250 filmes
		String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
		URI endereco = URI.create(url);

		// estaremos passando o objeto através do VAR e não pelo método do java eu
		// prefiro não usar o var mas enfim =D
		var client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		System.out.println(body);
		// extração dos dados que interessam (titulo, poster, classificação)

		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibir e manipular os dados

		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println("\u001b[1mTítulo: \u001b[m" + "\u001b[33m" + filme.get("title") + "\u001b[m");
			System.out.println("\u001b[1mURL do Pôster: \u001b[m" + "\u001b[33m" + filme.get("image") + "\u001b[m");

			double classificacao = Double.parseDouble(filme.get("imDbRating"));
			int numeroClaquetes = (int) classificacao;
			System.out.println("\u001b[1mAvaliação: \u001b[m" + "\u001b[33m" + filme.get("imDbRating") + "\u001b[m");

			for (int n = 1; n <= numeroClaquetes; n++) {
				if (classificacao >= 9) {
					System.out.print("\u2764");
				} else if (classificacao >= 8 && classificacao < 9) {
					System.out.print("\u2764");
				} else if (classificacao > 6 && classificacao < 8) {
					System.out.print("\u2764");
				} else if (classificacao >= 5 && classificacao <= 6) {
					System.out.print("\u2764");
				} else if (classificacao >= 3 && classificacao < 5) {
					System.out.print("\u2764");
				} else {
					System.out.print("\u2764");
				}
			}

			System.out.println("\n");

		}

	}

}
