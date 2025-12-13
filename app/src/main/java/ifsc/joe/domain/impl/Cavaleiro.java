package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {
    public static final String NOME_IMAGEM;
    public static final int VIDA_MAXIMA;
    public static final int ATAQUE;
    public static final int ALCANCE;

    private boolean atacando;
    private boolean montado;
    static {
        NOME_IMAGEM = "cavaleiro";
        VIDA_MAXIMA = 20;
        ATAQUE = 2;
        ALCANCE = 75;
    }

    public Cavaleiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM, VIDA_MAXIMA);
        this.atacando = false;
        this.montado = true;
        velocidade = 2 * VELOCIDADE_PADRAO;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }

    public boolean alcancou(Personagem inimigo) {
        int distanciaX = this.getPosX() - inimigo.getPosX();
        int distanciaY = this.getPosY() - inimigo.getPosY();

        int distancia = (int) Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));

        return distancia <= ALCANCE + LARGURA_E_ALTURA / 2;
    }

    public void alternarMontado() {
        this.montado = !this.montado;
        velocidade = this.montado ? velocidade * 2 : VELOCIDADE_PADRAO;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM + (this.atacando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA);

        g.drawOval(
            this.getPosX() - (ALCANCE - LARGURA_E_ALTURA / 2),
            this.getPosY() - (ALCANCE - LARGURA_E_ALTURA / 2),
            ALCANCE * 2,
            ALCANCE * 2
        );
    }
}
