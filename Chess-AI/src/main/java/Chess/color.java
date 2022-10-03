package Chess;

public enum color {
    White(){
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public int getOppositeDirection() {
            return 1;
        }

    } ,
    Black(){
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public int getOppositeDirection() {
            return -1;
        }
    };

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract int getDirection();

    public abstract int getOppositeDirection();
}
