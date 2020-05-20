package pl.karolinaglab.menugenerator.enumTypes;

public enum RecipeType {

    ALL_TYPES{
        @Override
        public String toString() {
            return "Wszystko";
        }
    },

    BREAKFAST{
        @Override
        public String toString() {
            return "Śniadanie";
        }
    },

    DINNER {
        @Override
        public String toString() {
            return "Obiad";
        }
    },

    SUPPER{
        @Override
        public String toString() {
            return "Kolacja";
        }
    },

    SECOND_MEAL{
        @Override
        public String toString() {
            return "Drugi posiłek";
        }
    }


}
