package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import javax.swing.*;
import java.util.Random;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {

    private final Random sorteio;
    private Tela tela;

    // Componentes da interface (gerados pelo Form Designer)
    private JPanel painelPrincipal;
    private JPanel painelTela;
    private JPanel painelLateral;
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private JRadioButton todosRadioButton;
    private JRadioButton aldeaoRadioButton;
    private JRadioButton arqueiroRadioButton;
    private JRadioButton cavaleiroRadioButton;
    private JButton atacarButton;
    private JButton buttonCima;
    private JButton buttonEsquerda;
    private JButton buttonBaixo;
    private JButton buttonDireita;
    private JLabel logo;

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
    }

    /**
     * Configura todos os listeners dos botões de movimento
     */
    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> this.condicionalParaMovimentar(Direcao.CIMA));
        buttonBaixo.addActionListener(e -> this.condicionalParaMovimentar(Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> this.condicionalParaMovimentar(Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> this.condicionalParaMovimentar(Direcao.DIREITA));
    }

    private void condicionalParaMovimentar(Direcao direcao) {
        if (this.todosRadioButton.isSelected()) {
            getTela().movimentarTodos(direcao);
        } else {
            if (this.aldeaoRadioButton.isSelected()) {
                getTela().movimentarAldeoes(direcao);
            }

            if (this.arqueiroRadioButton.isSelected()) {
                getTela().movimentarArqueiro(direcao);
            }

            if (this.cavaleiroRadioButton.isSelected()) {
                getTela().movimentarCavaleiro(direcao);
            }
        }
    }

    /**
     * Configura todos os listeners dos botões de criação
     */
    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> criarAldeaoAleatorio());

        bCriaCavaleiro.addActionListener(e -> criarCavaleiroAleatorio());

        bCriaArqueiro.addActionListener(e -> criarArqueiroAleatorio());
    }

    /**
     * Configura o listener do botão de ataque
     */
    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> {
            if (this.todosRadioButton.isSelected()) {
                getTela().atacarArqueiro();
                getTela().atacarCavaleiro();
            } else {
                if (this.arqueiroRadioButton.isSelected()) {
                    getTela().atacarArqueiro();
                }

                if (this.cavaleiroRadioButton.isSelected()) {
                    getTela().atacarCavaleiro();
                }
            }
        });
    }

    /**
     * Cria um aldeão em posição aleatória na tela.
     */
    private void criarAldeaoAleatorio() {
        final int PADDING = 65;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarAldeao(posX, posY);
    }

    private void criarCavaleiroAleatorio() {
        final int PADDING = 65;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarCavaleiro(posX, posY);
    }

    private void criarArqueiroAleatorio() {
        final int PADDING = 65;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarArqueiro(posX, posY);
    }

    /**
     * Exibe mensagem informando que a funcionalidade ainda não foi implementada.
     */
    private void mostrarMensagemNaoImplementado(String funcionalidade) {
        JOptionPane.showMessageDialog(
                painelPrincipal,
                "Preciso ser implementado",
                funcionalidade,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Obtém a referência da Tela com cast seguro.
     */
    private Tela getTela() {
        if (tela == null) {
            tela = (Tela) painelTela;
        }
        return tela;
    }

    /**
     * Retorna o painel principal para ser adicionado ao JFrame.
     */
    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    /**
     * Método chamado pelo Form Designer para criar componentes customizados.
     * Este método é invocado antes do construtor.
     */
    private void createUIComponents() {
        this.painelTela = new Tela();
    }
}