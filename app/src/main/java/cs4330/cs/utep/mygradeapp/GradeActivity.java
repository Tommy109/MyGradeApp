package cs4330.cs.utep.mygradeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {

    private TextView gradesView;
    private WebClient webClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gradesView = findViewById(R.id.grades);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String pin = intent.getStringExtra("pin");


        webClient = new WebClient(new WebClient.GradeListener() {
            @Override
            public void onGrade(String date, Grade grade) {
                gradesView.setText(Html.fromHtml(date + "<br/>" +
                        "Estimated Final Grade: " + grade.grade + "<br/>" +
                        "Weighted Total: " + grade.total + "<br/>"));

                System.out.println(date);

                for (Grade.Score score: grade.scores()) {
                    String scoreText = "<b>" + score.name + "</b>  " + 
                            score.earned +
                            "  (max: " + score.max + ") <br/>";
                    gradesView.append(Html.fromHtml(scoreText));
                }

                gradesView.append(Html.fromHtml("</table>"));
            }

            @Override
            public void onError(String msg) {
                gradesView.setText("Could not load grades");
            }
        });

        webClient.queryAsync(userId,pin);

    }
}
