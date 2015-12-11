package controller;

import actor.Enemy;
import actor.Player;

/**
 * Created by mikael.mathis on 04.12.2015.
 */
public final class BattleHandler {

    public static double calculateDamage(Player player){
        return 0;
    }

    public static void calcEnemyDeathReward(Player player, Enemy enemy){

        player.setGold(player.getGold() + 1000);
        player.setExp(player.getExp() + 500);

    }

}
