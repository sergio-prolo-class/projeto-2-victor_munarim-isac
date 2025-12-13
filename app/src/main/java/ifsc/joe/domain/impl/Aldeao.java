package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

import static ifsc.joe.Constantes.*;

public class Aldeao extends Personagem implements Coletador, ComMontaria {
    private boolean coletando;
    private boolean montado;

    public Aldeao(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM_ALDEAO, VIDA_MAXIMA_ALDEAO, CHANCE_ESQUIVA_ALDEAO);
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
        Image icone = this.carregarImagem(NOME_IMAGEM_ALDEAO + (coletando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
        }

        if (this.getEsquivou()) {
            this.desenhaEsquiva(g, painel, this.getPosX(), this.getPosY());
            this.setEsquivou(false);
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA_ALDEAO);
    }
}