package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import javax.swing.*;
import java.awt.event.ActionEvent;
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
    private JButton montarButton;
    private JLabel feedbackLabel;
    private JLabel placarArqueiro;
    private JLabel placarAldeao;
    private JLabel placarCavaleiro;

    private final InputMap mapaTeclas;
    private final ActionMap mapaAcoes ;

    //Inicializa o input e actions
    {
        mapaTeclas = getTela().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        mapaAcoes = getTela().getActionMap();
    }

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
        configurarTeclas();
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoMontar();
        configurarBotaoAtaque();
        configurarPlacar();
        placarAldeao.setText("0");
        placarArqueiro.setText("0");
        placarCavaleiro.setText("0");
    }

    /**
     * Configura todas as teclas.
     */
    private void configurarTeclas() {
        configurarTeclasMovimento();
        configurarTeclasCriacao();
        configurarTeclaMontar();
        configurarTeclaTab();
        configurarTeclaAtaque();
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

    /**
     * Configura as teclas para movimento
     */
    private void configurarTeclasMovimento() {
        mapaTeclas.put(KeyStroke.getKeyStroke("W"), "moverCima");
        mapaTeclas.put(KeyStroke.getKeyStroke("D"), "moverDireita");
        mapaTeclas.put(KeyStroke.getKeyStroke("A"), "moverEsquerda");
        mapaTeclas.put(KeyStroke.getKeyStroke("S"), "moverBaixo");

        mapaAcoes.put("moverCima", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                condicionalParaMovimentar(Direcao.CIMA);
            }
        });

        mapaAcoes.put("moverDireita", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                condicionalParaMovimentar(Direcao.DIREITA);
            }
        });

        mapaAcoes.put("moverEsquerda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                condicionalParaMovimentar(Direcao.ESQUERDA);
            }
        });

        mapaAcoes.put("moverBaixo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                condicionalParaMovimentar(Direcao.BAIXO);
            }
        });
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
     * Configura todas as teclas para criação
     */
    private void configurarTeclasCriacao() {
        mapaTeclas.put(KeyStroke.getKeyStroke("1"), "criarAldeao");
        mapaTeclas.put(KeyStroke.getKeyStroke("2"), "criarArqueiro");
        mapaTeclas.put(KeyStroke.getKeyStroke("3"), "criarCavaleiro");

        mapaAcoes.put("criarAldeao", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarAldeaoAleatorio();
            }
        });

        mapaAcoes.put("criarArqueiro", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarArqueiroAleatorio();
            }
        });

        mapaAcoes.put("criarCavaleiro", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarCavaleiroAleatorio();
            }
        });
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

    private void configurarTeclaAtaque() {
        mapaTeclas.put(KeyStroke.getKeyStroke(' '), "atacar");

        mapaAcoes.put("atacar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosRadioButton.isSelected()) {
                    getTela().atacarArqueiro();
                    getTela().atacarCavaleiro();
                } else {
                    if (arqueiroRadioButton.isSelected()) {
                        getTela().atacarArqueiro();
                    }
                    if (cavaleiroRadioButton.isSelected()) {
                        getTela().atacarCavaleiro();
                    }
                }
            }
        });
    }

    /**
     * Configura o listener do botão montar
     */
    private void configurarBotaoMontar() {
        montarButton.addActionListener(e -> {
            if (this.todosRadioButton.isSelected()) {
                getTela().alternarMontadoAldeao();
                getTela().alternarMontadoCavaleiro();
                feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado</div></html>");
            } else {
                if (this.aldeaoRadioButton.isSelected()) {
                    getTela().alternarMontadoAldeao();
                    feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado</div></html>");
                }

                if (this.cavaleiroRadioButton.isSelected()) {
                    getTela().alternarMontadoCavaleiro();
                    feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado</div></html>");
                }
            }
        });
    }

    /**
     * Configura a tecla para montar
     */
    private void configurarTeclaMontar() {
        mapaTeclas.put(KeyStroke.getKeyStroke("M"), "montar");

        mapaAcoes.put("montar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosRadioButton.isSelected()) {
                    getTela().alternarMontadoAldeao();
                    getTela().alternarMontadoCavaleiro();
                    feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado TODOS</div></html>");
                } else {
                    if (aldeaoRadioButton.isSelected()) {
                        getTela().alternarMontadoAldeao();
                        feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado dos ALDEÕES</div></html>");
                    }
                    if (cavaleiroRadioButton.isSelected()) {
                        getTela().alternarMontadoCavaleiro();
                        feedbackLabel.setText("<html><div style='width: 50px; text-align: justify;'>Estado montado alterado dos CAVALEIROS</div></html>");
                    }
                }
            }
        });
    }

    /**
     * Configura tecla tab para alterar a seleção
     */
    private void configurarTeclaTab() {
        mapaTeclas.put(KeyStroke.getKeyStroke('\t'), "trocarSelecao");

        mapaAcoes.put("trocarSelecao", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosRadioButton.isSelected()) {
                    aldeaoRadioButton.setSelected(true);
                    aldeaoRadioButton.requestFocus();
                } else if (aldeaoRadioButton.isSelected()) {
                    arqueiroRadioButton.setSelected(true);
                    arqueiroRadioButton.requestFocus();
                } else if (arqueiroRadioButton.isSelected()) {
                    cavaleiroRadioButton.setSelected(true);
                    cavaleiroRadioButton.requestFocus();
                } else if (cavaleiroRadioButton.isSelected()) {
                    todosRadioButton.setSelected(true);
                    todosRadioButton.requestFocus();
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

    private void configurarPlacar() {
        getTela().atualizaPlacar(placarAldeao, placarArqueiro, placarCavaleiro);
    }
}