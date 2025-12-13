package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

import static ifsc.joe.Constantes.*;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {
    private boolean atacando;
    private boolean montado;

    public Cavaleiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM_CAVALEIRO, VIDA_MAXIMA_CAVALEIRO, CHANCE_ESQUIVA_CAVALEIRO);
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

        return distancia <= ALCANCE_CAVALEIRO + LARGURA_E_ALTURA / 2;
    }

    public void alternarMontado() {
        this.montado = !this.montado;
        velocidade = this.montado ? velocidade * 2 : VELOCIDADE_PADRAO;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM_CAVALEIRO + (this.atacando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
        }

        if (this.getEsquivou()) {
            this.desenhaEsquiva(g, painel, this.getPosX(), this.getPosY());
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA_CAVALEIRO);

        g.drawOval(
            this.getPosX() - (ALCANCE_CAVALEIRO - LARGURA_E_ALTURA / 2),
            this.getPosY() - (ALCANCE_CAVALEIRO - LARGURA_E_ALTURA / 2),
            ALCANCE_CAVALEIRO * 2,
            ALCANCE_CAVALEIRO * 2
        );
    }
}
