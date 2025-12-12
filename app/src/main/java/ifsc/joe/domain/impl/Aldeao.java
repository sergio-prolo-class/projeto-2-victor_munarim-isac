package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;

public class Aldeao extends Personagem implements Coletador, ComMontaria {
    public static final String NOME_IMAGEM;
    public static final int VIDA_MAXIMA;

    private boolean coletando;
    private boolean montado;

    static {
        NOME_IMAGEM = "aldeao";
        VIDA_MAXIMA = 10;
    }

    public Aldeao(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM, VIDA_MAXIMA);
        this.montado = false;
        velocidade = VELOCIDADE_PADRAO;
    }

    public void coletar() {
        this.coletando = !this.coletando;
    }

    public void alternarMontado() {
        this.montado = !this.montado;
        velocidade = this.montado ? velocidade * 2 : VELOCIDADE_PADRAO;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM + (coletando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA);
    }
}