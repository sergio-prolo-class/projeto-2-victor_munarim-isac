package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Personagem {
    private int posX, posY;
    protected int vida, velocidade;
    protected static final int VELOCIDADE_PADRAO;
    private final Image icone;

    static {
        VELOCIDADE_PADRAO = 10;
    }

    public Personagem(int posX, int posY, String nomeImagem) {
        this.posX = posX;
        this.posY = posY;
        this.icone = this.carregarImagem(nomeImagem);
    }

    /**
     * Desenhando o Personagem, nas coordenadas X e Y, com a imagem 'icone'
     * no JPanel 'pai'
     *
     * @param g objeto do JPanel que será usado para desenhar o Aldeão
     */
    abstract public void desenhar(Graphics g, JPanel painel);

    /**
     * Atualiza as coordenadas X e Y do personagem
     *
     * @param direcao indica a direcao a ir.
     */
    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        switch (direcao) {
            case CIMA -> this.posY -= velocidade;
            case BAIXO -> this.posY += velocidade;
            case ESQUERDA -> this.posX -= velocidade;
            case DIREITA -> this.posX += velocidade;
        }

        //Não deixa a imagem ser desenhada fora dos limites do JPanel pai
        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        // 12 por conta da barra de vida
        this.posY = Math.min(Math.max(6, this.posY), maxAltura - this.icone.getHeight(null));
    }

    // Quando o personagem receber dano terá que dar um repaint na tela
    public void criarBarraVida(Graphics g, int vida, int VIDA_MAXIMA){
        int x = getPosX();
        int y = getPosY() - 6;
        int larguraTotal = 40;
        int altura = 5;

        // Fundo
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, larguraTotal, altura);

        // Vida atual
        int larguraVida = (vida * larguraTotal) / VIDA_MAXIMA;

        // Cor baseada na porcentagem
        double porcentagem = (double) vida / VIDA_MAXIMA;
        if (porcentagem > 0.75) {
            g.setColor(Color.GREEN);
        } else if (porcentagem > 0.25) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(x, y, larguraVida, altura);

        // Borda
        g.setColor(Color.BLACK);
        g.drawRect(x, y, larguraTotal, altura);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /**
     * Metodo auxiliar para carregar uma imagem do disco
     *
     * @param imagem Caminho da imagem
     * @return Retorna um objeto Image
     */
    protected Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./"+imagem+".png")
        )).getImage();
    }
}
