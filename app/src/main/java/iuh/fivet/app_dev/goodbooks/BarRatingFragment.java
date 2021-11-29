package iuh.fivet.app_dev.goodbooks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class BarRatingFragment extends Fragment {

    RatingBar ratingBar;
    Button btSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_rating, container, false);

        ratingBar = view.findViewById(R.id.ratingBar);
        btSubmit = view.findViewById(R.id.btnSubmitRating);

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

                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.fragment_bar_rating, container, false);
    }
}