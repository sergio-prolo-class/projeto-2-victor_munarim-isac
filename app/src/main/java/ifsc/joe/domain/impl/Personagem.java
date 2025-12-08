package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Personagem {
    private int posX, posY;
    private final Image icone;

    public Personagem(int posX, int posY, String nomeImagem) {
        this.posX = posX;
        this.posY = posY;
        this.icone = this.carregarImagem(nomeImagem);;
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
            case CIMA -> this.posY -= 10;
            case BAIXO -> this.posY += 10;
            case ESQUERDA -> this.posX -= 10;
            case DIREITA -> this.posX += 10;
        }

        //Não deixa a imagem ser desenhada fora dos limites do JPanel pai
        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
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
