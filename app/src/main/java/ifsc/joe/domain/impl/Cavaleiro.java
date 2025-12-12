package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {
    public static final String NOME_IMAGEM;
    public static final int VIDA_MAXIMA;
    public static final int ATAQUE;

    private boolean atacando;
    private boolean montado;
    static {
        NOME_IMAGEM = "cavaleiro";
        VIDA_MAXIMA = 20;
        ATAQUE = 2;
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
    }
}
