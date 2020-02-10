/* File Info 
 * Author:      Randolf 
 * CreateTime:  11/29/2019, 12:33:52 AM 
 * LastEditor:  Randolf 
 * ModifyTime:  11/29/2019, 12:33:55 AM 
 * Description: 定义游戏任务类
*/

package combogame.model;

// 命名规则: A(all)+消除总长+L(left)自己左边个数+右边个数+上面个数+下面个数
// Zero, One, Two , Three, Four, Five
public enum RemoveType {
    NULL(0), A3L2000(1), A3L1100(2), A3L0200(3), A3L0002(4), A3L0011(5), A3L0020(6), A4L3000(7), A4L2100(8), A4L1200(9),
    A4L0300(10), A4L0003(11), A4L0012(12), A4L0021(13), A4L0030(14), A5L4000(15), A5L3100(16), A5L2200(17), A5L1300(18),
    A5L0400(19), A5L0004(20), A5L0013(21), A5L0022(22), A5L0031(23), A5L0040(24);

    String typeName = "";
    int typeNumber = 0;

    // 无, xL2, xL1&xB1, xB2, yL2, yB1&yL1, yB2
    private RemoveType(int type) {
        this.typeNumber = type;
        switch (type) {
        case 0:
            this.typeName = "Null";
            break;
        case 1:
            this.typeName = "xL2";
            break;
        case 2:
            this.typeName = "xL1&&xR1";
            break;
        case 3:
            this.typeName = "xR2";
            break;
        case 4:
            this.typeName = "yD2";
            break;
        case 5:
            this.typeName = "yD1&&yU1";
            break;
        case 6:
            this.typeName = "yU2";
            break;
        case 7:
            this.typeName = "xL3";
            break;
        case 8:
            this.typeName = "xL2&&xR1";
            break;
        case 9:
            this.typeName = "xL1&&xR2";
            break;
        case 10:
            this.typeName = "xR3";
            break;
        case 11:
            this.typeName = "yD3";
            break;
        case 12:
            this.typeName = "yD2&yU1";
            break;
        case 13:
            this.typeName = "yD1&yU2";
            break;
        case 14:
            this.typeName = "yU3";
            break;
        case 15:
            this.typeName = "xL4";
            break;
        case 16:
            this.typeName = "xL3&&xR1";
            break;
        case 17:
            this.typeName = "xL2&&xR2";
            break;
        case 18:
            this.typeName = "xL1&&xR3";
            break;
        case 19:
            this.typeName = "xR4";
            break;
        case 20:
            this.typeName = "yD4";
            break;
        case 21:
            this.typeName = "yD3&yU1";
            break;
        case 22:
            this.typeName = "yD2&yU2";
            break;
        case 23:
            this.typeName = "yD1&yU3";
            break;
        case 24:
            this.typeName = "yU4";
            break;
        }
    }

    @Override
    public String toString() {
        return "Remove Type: " + this.typeName;
    }

    public int getTypeNumber() {
        return this.typeNumber;
    }
}
