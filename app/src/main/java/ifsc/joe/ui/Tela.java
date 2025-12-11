package ifsc.joe.ui;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Personagem;
import ifsc.joe.enums.Direcao;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

public class Tela extends JPanel {
    private final Set<Personagem> personagens;

    public Tela() {
        //TODO preciso ser melhorado
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO preciso ser melhorado

        // percorrendo a lista de aldeões e pedindo para cada um se desenhar na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g, this));

        // liberando o contexto gráfico
        g.dispose();
    }

    /**
     * Cria um aldeao nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarAldeao(int x, int y) {
        Aldeao aldeao = new Aldeao(x, y);
        aldeao.desenhar(super.getGraphics(), this);
        this.personagens.add(aldeao);
    }

    /**
     * Cria um cavaleiro nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de cavaleiros
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarCavaleiro(int x, int y) {
        Cavaleiro cavaleiro = new Cavaleiro(x, y);
        cavaleiro.desenhar(super.getGraphics(), this);
        this.personagens.add(cavaleiro);
    }

    public void criarArqueiro(int x, int y) {
        Arqueiro arqueiro = new Arqueiro(x, y);
        arqueiro.desenhar(super.getGraphics(), this);
        this.personagens.add(arqueiro);
    }

    /**
     * Atualiza as coordenadas X ou Y de todos os aldeoes
     *
     * @param direcao direcao para movimentar
     */
    public void movimentarTodos(Direcao direcao) {
        //TODO preciso ser melhorado

        this.personagens.forEach(personagem -> personagem.mover(direcao, this.getWidth(), this.getHeight()));

        // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
        this.repaint();
    }

    public void movimentarAldeoes(Direcao direcao) {
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Aldeao p) {
                p.mover(direcao, this.getWidth(), this.getHeight());
            }
        });

        this.repaint();
    }

    public void movimentarArqueiro(Direcao direcao) {
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Arqueiro p) {
                p.mover(direcao, this.getWidth(), this.getHeight());
            }
        });

        this.repaint();
    }

    public void movimentarCavaleiro(Direcao direcao) {
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Cavaleiro p) {
                p.mover(direcao, this.getWidth(), this.getHeight());
            }
        });

        this.repaint();
    }

    public void alternarMontadoCavaleiro(){
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Cavaleiro p) {
                p.alternarMontado();
            }
        });

        this.repaint();
    }

    public void alternarMontadoAldeao(){
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Aldeao p) {
                p.alternarMontado();
            }
        });

        this.repaint();
    }

    /**
     * Altera o estado do arqueiro de atacando para não atacando e vice-versa
     */
    public void atacarArqueiro() {
        //TODO preciso ser melhorado
        // Percorrendo a lista de aldeões e pedindo para todos atacarem
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Arqueiro p) {
                p.atacar();

                this.personagens.forEach(vitima -> {
                    if (vitima != p && this.estaProximo(p, vitima)) {
                        vitima.sofrerDano(Arqueiro.ATAQUE);
                    }
                });
            }
        });

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }

    public void atacarCavaleiro() {
        this.personagens.forEach(personagem -> {
            if (personagem instanceof Cavaleiro p) {
                p.atacar();

                this.personagens.forEach(vitima -> {
                    if (vitima != p && this.estaProximo(p, vitima)) {
                        vitima.sofrerDano(Arqueiro.ATAQUE);
                    }
                });
            }
        });

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }

    public boolean estaProximo(Personagem personagemUm, Personagem personagemDois) {
        return Math.abs(personagemUm.getPosX() - personagemDois.getPosX()) < 150
                && Math.abs(personagemUm.getPosY() - personagemDois.getPosY()) < 150;
    }
}