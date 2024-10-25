package model.EventListener;

import model.Tile.Player;
import model.TreasureType;

public interface PlayerStatusChangeListener {
    void PlayerStatusChanged(int player_id, TreasureType treasure_type) throws Exception;
    void PlayerStatusChangedTimeUp(int player_id, TreasureType treasure_type) throws Exception;
}
