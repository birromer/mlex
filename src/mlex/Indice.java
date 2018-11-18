package mlex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


public class Indice extends FileHandler
{
	private Map<Integer, List<String>> indiceLocal = new HashMap<Integer, List<String>>(); //objeto local do indice
	private String caminhoParaObjetoIndice = "./etc/objeto_indice";
	private String caminhoParaMapaJogoCategorias = "./etc/mapa_jogo_categorias";
	private String caminhoParaListaCategorias = "./etc/lista_categorias";
	private Map<Integer, String> mapaJogoCategorias = new HashMap<Integer, String>(); //dicionario que relaciona id do jogo com string contendo mapaJogoCategorias relacionadas a ele
	private List<String> listaCategorias = new ArrayList<String>(); //lista de mapaJogoCategorias disponiveis

	public Indice()
	{
		this.restauraObjetoIndice();
		this.restauraListaCategorias();
		this.restauraMapaJogoCategorias();
	}

	public void adicionaJogoNoIndice(Jogo jogo) throws Exception
	/*adiciona objeto jogo no objeto local do indice*/
	{
		if (this.testaJogoNoIndice(jogo.getIdJogo()) == true)
		{
			throw new Exception("Jogo ja existe");
		}

		List<String> linhaIndiceAdicionada = jogo.retornaListaAtributosRelevantes();
		indiceLocal.put(jogo.getIdJogo(), linhaIndiceAdicionada);
	}

	public void modificaJogoNoIndice(Jogo jogo) throws Exception
	/*atualiza objeto jogo no objeto local do indice de acordo com recebido*/
	{
		int idDoJogo = jogo.getIdJogo();

		if (mapaJogoCategorias.containsKey(idDoJogo) == false)
		{
			throw new Exception("Jogo nao existe no indice");
		}
		else
		{
			List<String> linhaIndiceAdicionada = jogo.retornaListaAtributosRelevantes();
			indiceLocal.replace(idDoJogo, linhaIndiceAdicionada);
		}
	}

	public void removeJogoDoIndice(int id) throws Exception
	/*remove objeto jogo no objeto local do indice*/
	{
		if (mapaJogoCategorias.containsKey(id) == false)
		{
			throw new Exception("jogo nao existe no indice");
		}
		else
		{
			mapaJogoCategorias.remove(id);
			indiceLocal.remove(id);
		}
	}

	public Map<Integer, List<String>> getIndiceLocal()
	{
		return indiceLocal;
	}
	
	public String getCategorias(int id)
	/*retorna string de 1's e 0's com as categorias pertencentes ao id recebido*/
	{
		String categs = mapaJogoCategorias.get(id);
		if ( categs == null)
		{
			if (listaCategorias.size() == 0)
			{
				return "0";
			}
			else
			{
				return new String(new char[listaCategorias.size()]).replace("\0", Integer.toString(0));
			}
		}
		else
		{
			return categs;
		}
	}

	public int getPosicaoCategoria(String categoria)
	/*retorna posicao de determinada categoria na string de categorias*/
	{
		return listaCategorias.indexOf(categoria);
	}

	public boolean testaCategoria(int id, String categoria)
	/*testa se jogo com id recebido possui a categoria parametro*/
	{
		int posicao = this.getPosicaoCategoria(categoria);
		String categoriasDoJogo = this.getCategorias(id);

		return(categoriasDoJogo.charAt(posicao) == '1');
	}

	int getNumeroJogos()
	/*retorna numero de jogos armazenados no indice local*/
	{
		return indiceLocal.size();
	}

	public int getNumeroCategorias()
	/*retorna numero de categorias armazenadas no objeto local que lista elas*/
	{
		if (listaCategorias == null)
		{
			return 0;
		}
		else
		{
			return listaCategorias.size();
		}	
	}

	boolean testaJogoNoIndice(int id)
	/*testa de jogo com id informado esta presente no indice*/
	{
		return indiceLocal.containsKey(id);
	}

	public void adicionaCategoriaAoIndice(String novaCategoria)
	/*adiciona categoria parametro nas estruturas relacionadas ao indice*/
	{	
		if (listaCategorias.contains(novaCategoria) == true)
		{
			return;
		}

		listaCategorias.add(novaCategoria);

		for (Integer key : mapaJogoCategorias.keySet())
		{
			if (this.getNumeroCategorias() > 1)
			{
				String novaListaCategorias = mapaJogoCategorias.get(key) + "0";
				mapaJogoCategorias.replace(key, novaListaCategorias);
			}
		}
	}

	public List<String> getListaCategorias()
	{
		return listaCategorias;
	}

	Map<Integer, String> getMapaCategorias()
	{
		return mapaJogoCategorias;
	}

	public void adicionaCategoriaAoJogo(int id, String categoria) throws Exception
	/*adiciona uma categoria na lista de categorias do jogo com id informado, equivalente a adicionar um jogo a categoria, porem o processo que ocorre eh o contrario*/
	{
		if (listaCategorias.contains(categoria) == false)
		{
			throw new Exception();
		}

		int posicaoCategoria = this.getPosicaoCategoria(categoria);
		String listaCategoriasDoJogo = mapaJogoCategorias.get(id);
		char[] listaAtualizadaDeCategoriasDoJogo = listaCategoriasDoJogo.toCharArray();
		listaAtualizadaDeCategoriasDoJogo[posicaoCategoria] = '1';
		mapaJogoCategorias.replace(id, String.valueOf(listaAtualizadaDeCategoriasDoJogo));
	}

	public void removeCategoriaDoJogo(int id, String categoria)
	/*muda para 0 o char que representa a categoria no jogo com id informado, equivalente a remover o jogo da categoria para o usuario*/
	{
		if (listaCategorias.contains(categoria) == false)
		{
			return;
		}

		int posicaoCategoria = this.getPosicaoCategoria(categoria);
		String listaCategoriasDoJogo = mapaJogoCategorias.get(id);
		char[] listaAtualizadaDeCategoriasDoJogo = listaCategoriasDoJogo.toCharArray();
		listaAtualizadaDeCategoriasDoJogo[posicaoCategoria] = '0';
		mapaJogoCategorias.replace(id, String.valueOf(listaAtualizadaDeCategoriasDoJogo));
	}

	public void novoJogoSendoAdicionado(int id)
	/*inicializa lista de categorias para os novos jogos sendo adicionados*/
	{
		mapaJogoCategorias.put(id, "0");
	}

	public List<String> getInformacoesJogoNoIndice(int id)
	/*retorna informacoes associadas ao jogo no indice local identificado pelo if informado*/
	{
		return indiceLocal.get(id);
	}
	
	public int getIdComNome(String nomeJogoProcurado)
	/*retorna id do jogo caso ele exista no indice local e -1 caso contrario*/
	{
		int id = -1;
		for (Integer key : indiceLocal.keySet())
		{
			if (this.getInformacoesJogoNoIndice(key).get(1).equals(nomeJogoProcurado))
			{
				id = key;
				return id;
			}
		}
		return id;
	}

	public List<Integer> filtroPorAtributos(String nomeOpcaoDeBusca, int opcaoDeBusca)
	/*retorna lista de ids validos dado filtro aplicado*/
	{
		List<Integer> ids = new ArrayList<Integer>();
		nomeOpcaoDeBusca = nomeOpcaoDeBusca.replace("\n", "");
				
		for (Integer key : indiceLocal.keySet())
		{
			if (this.getInformacoesJogoNoIndice(key).get(opcaoDeBusca).equals(nomeOpcaoDeBusca))
			{
				ids.add(key);
			}
		}
		return ids;
	}
	
	public void imprimeAlgunsJogos(List<Integer> idsProcurados)
	/*imprime jogos e suas informacoes na tela a partir de lista de ids a serem exibidos*/
	{
		for(Integer key : indiceLocal.keySet())
		{
			if(idsProcurados.contains(key))
					System.out.println("\nNome: " + this.getInformacoesJogoNoIndice(key).get(1) 
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(2)
						+ "\nDesenvolvedor: " + this.getInformacoesJogoNoIndice(key).get(3));
		}
	}
	
	public List<Integer> getIdsDoIndice()
	/*retorna lista com todos ids dos jogos armazenados no indice*/
	{
		List<Integer> idsDosJogos = new ArrayList<Integer>();
		for(Integer key : indiceLocal.keySet())
		{
			idsDosJogos.add(key);
		}
		return idsDosJogos;
	}
	
	public int filtroPorCategoria(String nomeCateg, List<Integer> idsValidos)
	/*verifica os ids validos e seleciona os que pertencem a categoria pesquisada*/
	{
		int resultados = -1;
		if(listaCategorias.contains(nomeCateg) == false)
		{
			return resultados;
		}
		
		resultados = 0;
		int posicaoDaCategoria = this.getPosicaoCategoria(nomeCateg);
				
		for (int i=0;i<idsValidos.size();i++)
		{
			int key = idsValidos.get(i);
			String listaCategoriasDoJogo = mapaJogoCategorias.get(key);
		
			char categTestada = listaCategoriasDoJogo.charAt(posicaoDaCategoria);
			if(categTestada == '1')
			{
				resultados++;
				System.out.println("\nNome: " + this.getInformacoesJogoNoIndice(key).get(1) 
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(2)
						+ "\nDesenvolvedor: " + this.getInformacoesJogoNoIndice(key).get(3));
			}
		}
		
		return resultados;
	}
	
	public void salvaObjetoIndice()
	/*salva o objeto do indice em arquivo binario na pasta /etc/ */
	{
		this.salvaObjetoEmArquivo(indiceLocal, caminhoParaObjetoIndice);
	}

	public void salvaMapaJogoCategorias()
	/*salva o objeto que mapeia jogo e sua lista de categorias em arquivo binario na pasta /etc/ */
	{
		this.salvaObjetoEmArquivo(mapaJogoCategorias, caminhoParaMapaJogoCategorias);
	}

	public void salvaListaCategorias()
	/*salva o objeto com a lista das categorias que existem em um arquivo binario na pasta /etc/ */
	{
		this.salvaObjetoEmArquivo(listaCategorias, caminhoParaListaCategorias);
	}

	public Object leArquivo(String caminhoParaArquivo)
	/*retorna objeto armazenado no arquivo especificado como parametro*/
	{
		File arquivo = new File(caminhoParaArquivo);
		try
		{
			FileInputStream fileIn = new FileInputStream(arquivo);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();

			objectIn.close();

			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void restauraObjetoIndice()
	/*restaura o objeto indice sempre a partir do mesmo arquivo*/
	{
		File arquivoObjetoIndice = new File(caminhoParaObjetoIndice);
		if (arquivoObjetoIndice.exists() == false)
		{
			System.out.println("arquivo do objeto indice para ser restaurado nao existe");
		}
		else
		{
			indiceLocal = (Map<Integer, List<String>>) this.leArquivo(caminhoParaObjetoIndice);	
		}
	}

	@SuppressWarnings("unchecked")
	public void restauraMapaJogoCategorias()
	/*restaura o objeto mapa de jogo para sua lista de categorias sempre a partir do mesmo arquivo*/
	{
		File arquivoMapaJogoCategorias = new File(caminhoParaMapaJogoCategorias);
		if (arquivoMapaJogoCategorias.exists() == false)
		{
			System.out.println("arquivo do mapa jogo categorias para ser restaurado nao existe");
		}
		else
		{
			mapaJogoCategorias = (Map<Integer, String>) this.leArquivo(caminhoParaMapaJogoCategorias);
		}
	}

	@SuppressWarnings("unchecked")
	public void restauraListaCategorias()
	/*restaura o objeto com a lista de todas categorias do indice sempre a partir do mesmo arquivo*/
	{
		File arquivoListaCategorias = new File(caminhoParaListaCategorias);
		if (arquivoListaCategorias.exists() == false)
		{
			System.out.println("arquivo da lista categorias para ser restaurado nao existe");
		}
		else
		{
			listaCategorias = (List<String>) (this.leArquivo(caminhoParaListaCategorias));
		}
	}
}
