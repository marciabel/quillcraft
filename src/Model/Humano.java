package Model;

import Business.NumberGenerator;

public class Humano extends Personaje{

    @Override
    public Integer atacar(Integer poderDefensaContrincante) {
        Integer efectividadDisparo = NumberGenerator.generateRandomPositiveInteger(1, 100);
        Integer valorAtaque = this.poderDisparo() * efectividadDisparo;

        return (((valorAtaque - poderDefensaContrincante)/500)*100);
    }
}
