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
	String path = "./etc/indice.txt";
	private Map<Integer, List<String>> indiceLocal = new HashMap<Integer, List<String>>(); //objeto local do indice
	private String caminhoParaObjetoIndice = "./etc/objeto_indice";
	private String caminhoParaMapaJogoCategorias = "./etc/mapa_jogo_categorias";
	private String caminhoParaListaCategorias = "./etc/lista_categorias";
	private Map<Integer, String> mapaJogoCategorias = new HashMap<Integer, String>(); //dicionario que relaciona id do jogo com string contendo mapaJogoCategorias relacionadas a ele
	private List<String> listaCategorias = new ArrayList<String>(); //lista de mapaJogoCategorias disponiveis

	public Indice()
	{
		this.restauraObjetoIndice();
		this.restauraMapaJogoCategorias();
		this.restauraListaCategorias();
	}

	public void adicionaJogoNoIndice(Jogo jogo) throws Exception
	{
		if (this.testaJogoNoIndice(jogo.getIdJogo()) == true)
		{
			System.out.println("Jogo ja existe no indice, sera ignorado");
			throw new Exception("Jogo ja existe");
		}

		List<String> linhaIndiceAdicionada = jogo.retornaListaAtributosRelevantes();
		indiceLocal.put(jogo.getIdJogo(), linhaIndiceAdicionada);
	}

	public void modificaJogoNoIndice(Jogo jogo) throws Exception
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
	{
		return listaCategorias.indexOf(categoria);
	}

	public boolean testaCategoria(int id, String categoria)
	{
		int posicao = this.getPosicaoCategoria(categoria);
		String categoriasDoJogo = this.getCategorias(id);

		return(categoriasDoJogo.charAt(posicao) == '1');
	}

	int getNumeroJogos()
	{
		return indiceLocal.size();
	}

	public int getNumeroCategorias()
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
	{
		return indiceLocal.containsKey(id);
	}

	public void adicionaCategoriaAoIndice(String novaCategoria)
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

	List<String> getListaCategorias()
	{
		return listaCategorias;
	}

	Map<Integer, String> getMapaCategorias()
	{
		return mapaJogoCategorias;
	}

	public void adicionaCategoriaAoJogo(int id, String categoria) throws Exception
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
	{
		mapaJogoCategorias.put(id, "0");
	}

	public List<String> getInformacoesJogoNoIndice(int id)
	{
		return indiceLocal.get(id);
	}
	
	public int getIdComNome(String nomeJogoProcurado) {
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
	{
		List<Integer> ids = new ArrayList<Integer>();
		for (Integer key : indiceLocal.keySet())
		{
			if (this.getInformacoesJogoNoIndice(key).get(opcaoDeBusca) == nomeOpcaoDeBusca)
			{
				ids.add(key);
//		possivel implementacao de similaridade de strings pra uma pesquisa mais legal
//			import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler    
//			public double compareStrings(String stringA, String stringB)
//			{
//			    JaroWinkler algorithm = new JaroWinkler();
//			    return algorithm.getSimilarity(stringA, stringB);
//			}
//				
			}
		}
		return ids;
	}
	
	public void imprimeAlgunsJogos(List<Integer> idsProcurados)
	{
		for(Integer key : indiceLocal.keySet())
		{
			if(idsProcurados.contains(key))
					System.out.println("\nNome: " + this.getInformacoesJogoNoIndice(key).get(1) 
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(2)
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(3));
		}
	}
	
	public List<Integer> getIdsDoIndice()
	{
		List<Integer> idsDosJogos = new ArrayList<Integer>();
		for(Integer key : indiceLocal.keySet())
		{
			idsDosJogos.add(key);
		}
		return idsDosJogos;
	}
	
	public int filtroPorCategoria(String nomeCateg, List<Integer> idsValidos)
	{
		int resultados = -1;
		if(listaCategorias.contains(nomeCateg) == false)
			return resultados;
		
		resultados = 0;
		int posicaoDaCategoria = this.getPosicaoCategoria(nomeCateg);
		
		for (int i=0;i<idsValidos.size();i++)
		{
			int key = idsValidos.get(i);
			String listaCategoriasDoJogo = mapaJogoCategorias.get(key);
			char[] jogoTemCategoria = listaCategoriasDoJogo.toCharArray();
			if(jogoTemCategoria[posicaoDaCategoria] == '1')
			{
				resultados++;
				System.out.println("\nNome: " + this.getInformacoesJogoNoIndice(key).get(1) 
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(2)
						+ "\nLancamento: " + this.getInformacoesJogoNoIndice(key).get(3));
			}
		}
		return resultados;
	}
	
	public void salvaObjetoIndice()
	{
		this.salvaObjetoEmArquivo(indiceLocal, caminhoParaObjetoIndice);
	}

	public void salvaMapaJogoCategorias()
	{
		this.salvaObjetoEmArquivo(mapaJogoCategorias, caminhoParaMapaJogoCategorias);
	}

	public void salvaListaCategorias()
	{
		this.salvaObjetoEmArquivo(listaCategorias, caminhoParaListaCategorias);
	}

	Object leArquivo(String caminhoParaArquivo)
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
