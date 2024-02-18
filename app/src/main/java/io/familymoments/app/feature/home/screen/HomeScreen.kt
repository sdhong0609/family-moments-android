package io.familymoments.app.feature.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import io.familymoments.app.R
import io.familymoments.app.core.theme.AppColors
import io.familymoments.app.core.theme.AppTypography
import io.familymoments.app.feature.home.component.postItemContentShadow
import io.familymoments.app.feature.home.model.Post
import io.familymoments.app.feature.home.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel,
    navigateToPostDetail: () -> Unit
) {
    val homeUiState = viewModel.homeUiState.collectAsStateWithLifecycle()
    val posts = homeUiState.value.posts

    LaunchedEffect(Unit) {
        // TODO: 추후 familyId 수정 예정
        viewModel.getPosts(2)
    }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 100.dp)
    ) {
        item {
            HomeScreenTitle()
        }
        items(posts) { post ->
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                PostItemHeader(post = post)
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.postItemContentShadow()) {
                    PostItemContent(post = post, navigateToPostDetail = navigateToPostDetail)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun HomeScreenTitle() {
    Spacer(modifier = Modifier.height(69.dp))
    Text(
        text = stringResource(id = R.string.home_greeting_1, "딸내미"),
        style = AppTypography.B2_14,
        color = AppColors.black2
    )
    Text(
        text = buildAnnotatedString {
            withStyle(style = AppTypography.B1_16.toSpanStyle().copy(color = AppColors.black2)) {
                append(stringResource(id = R.string.home_greeting_2))
            }
            append(" ")
            withStyle(style = AppTypography.H2_24.toSpanStyle().copy(color = AppColors.black2)) {
                append(stringResource(id = R.string.home_greeting_3, 2))
            }
            append(" ")
            withStyle(style = AppTypography.B1_16.toSpanStyle().copy(color = AppColors.black2)) {
                append(stringResource(id = R.string.home_greeting_4))
            }
        }
    )
    Spacer(modifier = Modifier.height(9.dp))
    Spacer(
        modifier = Modifier
            .width(67.dp)
            .height(1.dp)
            .background(AppColors.deepPurple1)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun PostItemHeader(post: Post) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(39.dp)
                .clip(shape = CircleShape),
            model = post.profileImg,
            contentDescription = "profileImg"
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = post.writer,
            style = AppTypography.LB1_13,
            color = AppColors.black2
        )
        Text(
            text = post.createdAt.formattedDate(),
            style = AppTypography.LB2_11,
            color = AppColors.grey3
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItemContent(
    post: Post,
    navigateToPostDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 11.dp)
            .heightIn(min = 282.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(AppColors.grey6)
            .clickable { navigateToPostDetail() }
    ) {
        Box(
            modifier = Modifier
                .height(168.dp)
        ) {
            val pagerState = rememberPagerState(pageCount = { post.imgs.size })
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    model = post.imgs[index],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
            if (post.imgs.size > 1) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 6.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) AppColors.purple2 else AppColors.grey6
                        Box(
                            modifier = Modifier
                                .padding(horizontal = (3.5).dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(7.dp)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 11.dp, end = 10.dp)
                    .weight(1f),
                text = post.content,
                style = AppTypography.B2_14,
                color = AppColors.black2
            )
            Column(
                modifier = Modifier.padding(top = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_three_dots_row),
                    tint = AppColors.deepPurple1,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(6.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (post.loved) R.drawable.ic_heart_filled else R.drawable.ic_heart_empty
                    ),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "3", style = AppTypography.LB2_11, color = AppColors.black1)
            }
        }
    }
}

private fun String.formattedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("yyyy.MM.dd(EEE)", Locale.getDefault())
    return outputFormat.format(date ?: Date())
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item {
            HomeScreenTitle()
        }
        items(10) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(39.dp)
                            .clip(shape = CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_sample_dog),
                            contentScale = ContentScale.Crop,
                            contentDescription = "profile"
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "딸내미",
                        style = AppTypography.LB1_13,
                        color = AppColors.black2
                    )
                    Text(
                        text = "2023.06.12(토)",
                        style = AppTypography.LB2_11,
                        color = AppColors.grey3
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.postItemContentShadow()) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 11.dp)
                            .heightIn(min = 282.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(AppColors.grey6)
                            .clickable { }
                    ) {
                        Box(
                            modifier = Modifier
                                .height(168.dp)
                        ) {
                            val pagerState = rememberPagerState(pageCount = { 3 })
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.img_sample_trees),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 6.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                repeat(pagerState.pageCount) { iteration ->
                                    val color =
                                        if (pagerState.currentPage == iteration) AppColors.purple2 else AppColors.grey6
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = (3.5).dp)
                                            .clip(CircleShape)
                                            .background(color)
                                            .size(7.dp)
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 18.dp, end = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 11.dp, end = 10.dp)
                                    .weight(1f),
                                text = "우리 가족사진 삼각대로 찍기~~\n너무 잘나왔다!",
                                style = AppTypography.B2_14,
                                color = AppColors.black2
                            )
                            Column(
                                modifier = Modifier.padding(top = 5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_three_dots_row),
                                    tint = AppColors.deepPurple1,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_heart_empty),
                                    tint = AppColors.deepPurple1,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(text = "3", style = AppTypography.LB2_11, color = AppColors.black1)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
