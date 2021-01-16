package io.services.backlog.model;

public enum Level {
    P_1(1),
    P_2(2),
    P_3(3),
    P_4(4),
    P_5(5),
    P_6(6),
    S_1(7),
    S_2(8),
    S_3(9),
    S_4(10),
    S_5(11),
    S_6(12),
    BAC(13),
    U_1(14),
    U_2(15),
    U_3(16),
    U_4(17),
    U_5(18),
    E_1(19),
    E_2(20),
    E_3(21),
    E_4(22),
    E_5(23),
    PRO_1(24),
    PRO_2(25),
    PRO_3(26),
    PRO_4(27),
    PRO_5(28);
    private int id;

    Level(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
