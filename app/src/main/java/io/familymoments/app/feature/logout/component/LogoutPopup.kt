package io.familymoments.app.feature.logout.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.familymoments.app.R
import io.familymoments.app.core.theme.AppColors
import io.familymoments.app.core.theme.AppTypography

@Composable
fun LogoutPopup(
    onDismissRequest: () -> Unit = {},
    onLogoutRequest: () -> Unit = {},
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(AppColors.grey6),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.End)
                        .padding(top = 14.dp, end = 14.dp, bottom = 10.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .clickable { onDismissRequest() }
                            .align(Alignment.Center),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_album_popup_close),
                        contentDescription = "close popup",
                    )
                }
                Text(
                    text = stringResource(id = R.string.logout_content),
                    style = AppTypography.BTN4_18,
                    color = AppColors.deepPurple1,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 33.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 17.dp)
                        .padding(bottom = 12.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        modifier = Modifier
                            .height(54.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(AppColors.purple2, AppColors.grey6),
                        shape = RoundedCornerShape(60.dp),
                        onClick = onLogoutRequest
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout_btn),
                            style = AppTypography.BTN4_18,
                            color = AppColors.grey6
                        )
                    }
                    Spacer(modifier = Modifier.width(22.dp))
                    Button(
                        modifier = Modifier
                            .height(54.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(AppColors.purple1, AppColors.grey6),
                        shape = RoundedCornerShape(60.dp),
                        onClick = onDismissRequest
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout_cancel_btn),
                            style = AppTypography.BTN4_18,
                            color = AppColors.grey6
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutPopupPreview() {
    LogoutPopup()
}
