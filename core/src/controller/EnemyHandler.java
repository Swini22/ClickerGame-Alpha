package controller;

import actor.Enemy;

/**
 * Created by mikael.mathis on 01.12.2015.
 */
public final class EnemyHandler {

    public static void damageEnemy(Enemy enemy){
        enemy.setLifePoints(enemy.getLifePoints() - 5000);
    }

}
