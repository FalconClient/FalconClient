package ir.albino.client.matrix;

import com.cosium.matrix_communication_client.CreateRoomInput;
import com.cosium.matrix_communication_client.MatrixResources;
import com.cosium.matrix_communication_client.Message;
import com.cosium.matrix_communication_client.RoomResource;

public class MatrixBridge {
    private MatrixResources matrix;
    private RoomResource room;

    public MatrixBridge(MatrixConfiguration config) {
        matrix = MatrixResources.factory()
                .builder()
                .https()
                .hostname("wiiz.ir")
                .defaultPort()
                .usernamePassword(config.userName, config.password)
                .build();

        room = matrix
                .rooms()
                .create(
                        CreateRoomInput.builder()
                                .name(config.userName + "'s room")
                                .roomAliasName(config.userName)
                                .topic("DM " + config.userName)
                                .build());


    }

    public MatrixResources getMatrix() {
        return matrix;
    }

    public RoomResource getRoom() {
        return room;
    }


}
