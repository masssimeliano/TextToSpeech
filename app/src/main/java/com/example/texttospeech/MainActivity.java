package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private ImageView mImageViewFast, mImageViewSlow;
    private EditText mEditText;
    private Spinner mSpinner;
    private SeekBar mSeekBar;

    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewFast = findViewById(R.id.mImageViewFast);
        mImageViewSlow = findViewById(R.id.mImageViewSlow);
        mSpinner = findViewById(R.id.mSpinner);

        mEditText = findViewById(R.id.mEditText);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTTS.setLanguage(Locale.ENGLISH);
                if (status == TextToSpeech.SUCCESS)
                {
                    if ((mTTS.setLanguage(Locale.ENGLISH) == TextToSpeech.LANG_MISSING_DATA) || (mTTS.setLanguage(Locale.ENGLISH) == TextToSpeech.LANG_NOT_SUPPORTED))
                        Log.d("TTS", "Language not supported");
                    else {
                        mImageViewFast.setEnabled(true);
                        mImageViewSlow.setEnabled(true);
                    }
                }
                else
                    Log.d("TTS", "Initialization failed");
            }
        });

        mSeekBar = findViewById(R.id.mSeekBar);

        mTTS.setPitch((float) 1);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTTS.setPitch((float) (1 + (progress - 5) / 10.0));

                Log.d("Progress", Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.languages);

                mTTS.setLanguage(new Locale(chooseLanguage(choose[selectedItemPosition])));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mImageViewFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTTS.setSpeechRate((float) 1);
                speakText();
            }
        });
        mImageViewSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTTS.setSpeechRate((float) 0.1);
                speakText();
            }
        });
    }

    public String chooseLanguage(String language) {
        switch (language) {
            case "English, US":
                return "en_US";
            case "German, Germany":
                return "de_DE";
            case "Chinese, PRC":
                return "zh_CN";
            case "Chinese, Taiwan":
                return "zh_TW";
            case "Czech, Czech Republic":
                return "cs_CZ";
            case "Dutch, Belgium":
                return "nl_BE";
            case "Dutch, Netherlands":
                return "nl_NL";
            case "English, Australia":
                return "en_AU";
            case "English, Britain":
                return "en_GB";
            case "English, Canada":
                return "en_CA";
            case "English, New Zealand":
                return "en_NZ";
            case "English, Singapor":
                return "en_SG";
            case "French, Belgium":
                return "fr_BE";
            case "French, Canada":
                return "fr_CA";
            case "French, France":
                return "fr_FR";
            case "French, Switzerland":
                return "fr_CH";
            case "German, Austria":
                return "de_AT";
            case "German, Liechtenstein":
                return "de_LI";
            case "German, Switzerland":
                return "de_CH";
            case "Italian, Italy":
                return "it_IT";
            case "Italian, Switzerland":
                return "it_CH";
            case "Japanese":
                return "ja_JP";
            case "Korean":
                return "ko_KR";
            case "Polish":
                return "pl_PL";
            case "Russian":
                return "ru_RU";
            case "Spanish":
                return "es_ES";
            case "Arabic, Egypt":
                return "ar_EG";
            case "Arabic, Israel":
                return "ar_IL";
            case "Bulgarian, Bulgaria":
                return "bg_BG";
            case "Catalan, Spain":
                return "ca_ES";
            case "Croatian, Croatia":
                return "hr_HR";
            case "Danish, Denmar":
                return "da_DK";
            case "English, India":
                return "en_IN";
            case "English, Ireland":
                return "en_IE";
            case "English, Zimbabwe":
                return "en_ZA";
            case "Finnish, Finland":
                return "fi_FI";
            case "Greek, Greece":
                return "el_GR";
            case "Hebrew, Israel":
                return "iw_IL";
            case "Hindi, India":
                return "hi_IN";
            case "Hungarian, Hungary":
                return "hu_HU";
            case "Indonesian, Indonesia":
                return "in_ID";
            case "Latvian, Latvia":
                return "lv_LV";
            case "Lithuanian, Lithuania":
                return "lt_LT";
            case "Norwegian-Bokmol, Norwa":
                return "nb_NO";
            case "Portuguese, Brazil":
                return "pt_BR";
            case "Portuguese, Portugal":
                return "pt_PT";
            case "Romanian, Romania":
                return "ro_RO";
            case "Serbian":
                return "sr_RS";
            case "Slovak, Slovakia":
                return "sk_SK";
            case "Slovenian, Slovenia":
                return "sl_SI";
            case "Spanish, US":
                return "es_US";
            case "Swedish, Sweden":
                return "sv_SE";
            case "Tagalog, Philippines":
                return "tl_PH";
            case "Thai, Thailand":
                return "th_TH";
            case "Turkish, Turkey":
                return "tr_TR";
            case "Ukrainian, Ukraine":
                return "uk_UA";
            case "Vietnamese, Vietnam":
                return "vi_VN";
            default:
                return "";
        }
    }
    public void speakText() {
        if (mEditText.getText().toString() != null) {
            mTTS.speak(mEditText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
        else {
            Toast.makeText(this, "There is no text", Toast.LENGTH_LONG).show();
            Log.d("TTS", "No text");
        }
    }
}