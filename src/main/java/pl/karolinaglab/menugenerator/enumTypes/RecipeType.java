package pl.karolinaglab.menugenerator.enumTypes;

public enum RecipeType {

    BREAKFAST{
        @Override
        public String toString() {
            return "Sniadanie";
        }
    },

    DINNER
            {
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

    DESSERT{
        @Override
        public String toString() {
            return "Deser";
        }
    }


}
