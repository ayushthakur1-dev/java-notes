// it is a creational design pattern, used to create complex objects, specially those
// having optional fields
// eg. StringBuilder

class Builder {
    public static void main(String[] args) {

    }
}

class Car {
    private int door;
    private boolean roof;
    private int seat;
    private String engine;

    private Car(Builder builder) {
        this.door = builder.door;
        this.roof = builder.roof;
        this.seat = builder.seat;
        this.engine = builder.engine;
    }

    public static class Builder {
        private int door = 4;
        private boolean roof = true;
        private int seat = 5;
        private String engine;

        public Builder(String engine) {
            this.engine = engine;
        }

        public void door(int door) {
            this.door = door;
        }

        public void roof(boolean roof) {
            this.roof = roof;
        }

        public void seat(int seat) {
            this.seat = seat;
        }

        public Car build() {
            return new Car(this);
        }
    }
}