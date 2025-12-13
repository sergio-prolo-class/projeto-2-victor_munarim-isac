package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

import static ifsc.joe.Constantes.*;

public class Arqueiro extends Personagem implements Guerreiro, Coletador {
    private boolean atacando;
    private boolean coletando;

    public Arqueiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM_ARQUEIRO, VIDA_MAXIMA_ARQUEIRO, CHANCE_ESQUIVA_ARQUEIRO);
        this.atacando = false;
        velocidade = VELOCIDADE_PADRAO;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }

    public void coletar() {
        this.coletando = !this.coletando;
    }

    public boolean alcancou(Personagem inimigo) {
        int distanciaX = this.getPosX() - inimigo.getPosX();
        int distanciaY = this.getPosY() - inimigo.getPosY();

        int distancia = (int) Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));

        return distancia <= ALCANCE_ARQUEIRO + LARGURA_E_ALTURA / 2;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM_ARQUEIRO + (this.atacando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
        }

        if (this.getEsquivou()) {
            this.desenhaEsquiva(g, painel, this.getPosX(), this.getPosY());
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA_ARQUEIRO);

        g.drawOval(
            this.getPosX() - (ALCANCE_ARQUEIRO - LARGURA_E_ALTURA / 2),
            this.getPosY() - (ALCANCE_ARQUEIRO - LARGURA_E_ALTURA / 2),
            ALCANCE_ARQUEIRO * 2,
            ALCANCE_ARQUEIRO * 2
        );
    }
}