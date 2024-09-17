package com.choucair.questions;

import com.choucair.interactions.ScrollFor;
import com.choucair.ui.RecruitmentPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class CandidateRecord implements Question<Boolean> {

    public static Question<Boolean> verify() {
        return new CandidateRecord();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String status = "Hired";
        String name = actor.recall("fullName");
        String vacancy = actor.recall("vacancy");
        String hiringManager = actor.recall("hiringManager");
        String dateOfApplication = actor.recall("date");
        boolean isMatch = false;
        List<WebElementFacade> rows = RecruitmentPage.CANDIDATE_ROW.resolveAllFor(actor);

        for (WebElementFacade row : rows) {

            actor.attemptsTo(
                    ScrollFor.element(row)
            );

            boolean hasName = Text.of(RecruitmentPage.CELL_WITH_TEXT.of(name)).asString().answeredBy(actor).equals(name);
            boolean hasVacancy = Text.of(RecruitmentPage.CELL_WITH_TEXT.of(vacancy)).asString().answeredBy(actor).equals(vacancy);
            boolean hasHiringManager = Text.of(RecruitmentPage.CELL_WITH_TEXT.of(hiringManager)).asString().answeredBy(actor).equals(hiringManager);
            boolean hasDateOfApplication = Text.of(RecruitmentPage.CELL_WITH_TEXT.of(dateOfApplication)).asString().answeredBy(actor).equals(dateOfApplication);
            boolean hasStatus = Text.of(RecruitmentPage.CELL_WITH_TEXT.of(status)).asString().answeredBy(actor).equals(status);
            if (hasName && hasVacancy && hasHiringManager && hasDateOfApplication && hasStatus) {
                actor.attemptsTo(
                        Click.on(RecruitmentPage.INPUT_IN_ROW)
                );
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }
}

