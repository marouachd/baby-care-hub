package co.simplon.babycarehub.meals;

import java.sql.Date;

public interface MealDetail {

    String getType();

    SnackEntity getSnackId();

    String getEval();

    String getCommentaire();

    Long getChildId();

    Date getDate();

}
