package iuh.fivet.app_dev.goodbooks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BarRatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button btSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar);

        ratingBar = findViewById(R.id.ratingBar);
        btSubmit = findViewById(R.id.btnSubmitRating);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ratingStar = (int) ratingBar.getRating();
                String message = "";

                switch (ratingStar) {
                    case 0:
                        message = "Very bad book \uD83D\uDE1E ";
                        break;
                    case 1:
                        message = "A bad book \uD83D\uDE41 ";
                        break;
                    case 2:
                        message = "Not interest book \uD83D\uDE15 ";
                        break;
                    case 3:
                        message = "A few interest \uD83D\uDE09 ";
                        break;
                    case 4:
                        message = "A nice book \uD83D\uDE03 ";
                        break;
                    case 5:
                        message = "Goodread \uD83D\uDE0D ";
                        break;
                    default:
                        message = "";
                        break;
                }

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
