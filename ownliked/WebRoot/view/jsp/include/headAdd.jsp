<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="add" class="ModalContainer visible">
			<div class="modal wide PaddingLess">
				<div class="header lg">
					<a href="#" class="close" onclick="Modal.close('add'); return false;">
						<strong>Close</strong>
						<span></span>
					</a>
					<h2>Add</h2>
				</div>
				<p id="clipIt"></p>
				<div id="openLinks">
					<a id="openScrapePin" class="cell" onclick="AddDialog.close('add', 'scrapePin'); return false;">
						<div class="icon" id="scrape"></div>
						<span>Add a Clip</span>
					</a>
					<a id="openUploadPin" class="cell" onclick="AddDialog.close('add', 'uploadPin');return false;">
						<div class="icon" id="upload"></div>
						<span>Update a Clip</span>
					</a>
					<a id="openCreateBoard" class="cell" onclick="AddDialog.close('add', 'createBoard');return false;">
						<div class="icon" id="board"></div>
						<span>Create a Clip</span>
					</a>
				</div>
			</div>
			<div class="overlay"></div>
		</div>
<%--	Add Modal	--%>
		<div id="uploadPin" class="ModalContainer visible">
			<div class="modal wide PaddingLess">
				<div class="header lg">
					<a class="close" onclick="AddDialog.childClose('add', 'uploadPin'); return false;">
						<strong>Close</strong>
						<span></span>
					</a>
					<h2>Upload a Clip</h2>
				</div>
				<div class="pinTop">
					<form action="ownClip/uploadClip.h" method="post">
						<input type="hidden" id="boardId" name="boardId" value="1"/>
						<input type="hidden" id="boardName" name="boardName" value=""/>
						<input type="hidden" id="upImage" name="image"/>
						<input type="hidden" id="description" name="description" value=""/>
						<input type="file" name="img" id="upimg"/>
					</form>
				</div>
				<div class="pinBottom">
					<div class="imagePicker">
						<img src="" class="load" alt=""/>
						<div class="images pin" style="margin-bottom: -179.5px; ">
							<div class="price"></div>
							<ul>
								<li><img src="view/images/popular/gif.gif" alt=""/></li>
							</ul>
						</div>
					</div>
					<div class="PinForm">
						<div class="BoardListOverlay"></div>
						<div class="BoardSelector BoardPicker">
							<div class="current">
								<span class="CurrentBoard" data-id="">Create New Board</span>
								<span class="DownArrow"></span>
							</div>
							<div class="BoardList">
								<div class="wrapper">
									<ul>
										
									</ul>
									<div class="CreateBoard">
										<input type="text" value="Create New Board"/>
										<button class="button whiteButton button18 noFloat" type="button">Create</button>
										<div class="CreateBoardStatus"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="InputArea">
							<ul class="form fancyForm">
								<li class="noMarginBottom val">
									<textarea class="DescriptionTextarea" rows="2"></textarea>
									<div class="tagmate-menu" style="position: absolute; display: none; "></div>
									<label>Describe your pin…</label>
									<span class="fff"></span>
								</li>
							</ul>
						</div>
						<div class="CreateBoardStatus error mainerror"></div>
						<div class="buttons">
							<button class="button button18 redButton" type="button">Clip It</button>
							<span class="CharacterCount colorless">500</span>
						</div>
					</div>
				</div>
			</div>
			<div class="overlay"></div>
		</div>
<%--	upload clip Modal	--%>
		<div id="createBoard" class="ModalContainer visible">
			<div class="modal wide" style="margin-bottom: -236px; ">
				<div class="header lg">
					<a class="close" onclick="AddDialog.childClose('add', 'createBoard'); return false;">
						<strong>Close</strong>
						<span></span>
					</a>
					<h2>创建板块</h2>
				</div>
				<form class="form staticForm noMargin">
					<ul>
						<li class="noBoardTop">
							<input type="text" id="boardName" name="boardName"/>
							<label>板块标题</label>
							<span></span>
						</li>
						<li>
							<input type="hidden" id="category" name="category"/>
							<div class="BoardListOverlay" style="display: none;"></div>
							<div id="categoryPicker" class="BoardSelector BoardPicker">
								<div class="current">
									<span class="CurrentBoard" data-id="0">Select a Category</span>
									<span class="DownArrow"></span>
								</div>
								<div class="BoardList">
									<div class="wrapper">
										<ul>
											<c:forEach items="${ownBoards}" var="v" varStatus="s">
												<li>
													<span class="submenuColumn" data-id="${v.id}">${v.boardName }</span>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<label>板块类型</label>
						</li>
					</ul>
					<div class="submit">
						<a class="button redButton button18">Create Board</a>
					</div>
					<div class="createBoardStatus error"></div>
				</form>
			</div>
			<div class="overlay"></div>
		</div>
<%--	create board	--%>
