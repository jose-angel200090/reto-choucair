package com.choucair.stepdefinitions;

import com.choucair.models.Candidate;
import com.choucair.questions.CandidateRecord;
import com.choucair.tasks.AddCandidate;
import com.choucair.tasks.CompleteHiring;
import com.choucair.tasks.Login;
import com.choucair.tasks.NavigateToRecruitment;
import com.choucair.ui.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;

public class RecruitmentStepDefintions {
    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActor("user");
    }

    @Given("the recruitment manager is on the login page")
    public void theRecruitmentManagerIsOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Open.browserOn().the(LoginPage.class)
        );
    }

    @When("they log in with valid credentials")
    public void theyLogInWithValidCredentials() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Login.withCredentials("Admin", "admin123")
        );
    }

    @And("they navigate to the Recruitment section")
    public void theyNavigateToTheRecruitmentSection() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateToRecruitment.section()
        );
    }

    @And("they add a new candidate with valid details")
    public void theyAddANewCandidateWithValidDetails() {
        Candidate candidate = new Candidate("Jose", "Angel", "Lopez", "jose@gmail.com", "3015454901");
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddCandidate.withDetails(candidate)
        );
    }

    @And("they finish the complete the hiring process")
    public void theyFinishTheCompleteTheHiringProcess() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CompleteHiring.process()
        );
    }

    @Then("they should see the candidate is hired")
    public void theyShouldSeeTheCandidateIsHired() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that("the candidate's data matches the filled forms and the status is 'Hired'", CandidateRecord.verify()).isEqualTo(true)
        );
    }

}
