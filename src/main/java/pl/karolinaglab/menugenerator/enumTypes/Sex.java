package pl.karolinaglab.menugenerator.enumTypes;

public enum Sex {

    MALE{
        @Override
        public String toString() {
            return "male";
        }
    },
    FEMALE{
        @Override
        public String toString() {
            return "female";
        }
    }
}
