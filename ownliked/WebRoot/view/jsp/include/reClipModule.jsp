<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="ajax PinCreate Module inModal" style="display: block;">
	<div class="moduleMask"></div>
	<div class="PinForm ajax Module">
		<form class="standardForm">
			<h1>Reclip a Clip</h1>
			<div class="ajax ui-PinPreview Module">
				<img src="${oc.image }" style="" alt="" class="image pinPreviewImg">
			</div>
			<ul>
				<li class="boardWrapper">
					<h3>
						<label>Board</label>
					</h3>
					<div>
						<div class="ajax medium BoardPicker Module">
							<div class="boardPickerInnerWrapper">
								<div class="createBoard">
									<button type="button"
										class="rounded createBoardButton Button hasText Module ajax btn">
										<span class="buttonText">Create</span>
									</button>
									<div class="createBoardNameContainer">
										<input type="text" name="name" class="createBoardName" placeholder="Create New Board" autofocus="">
									</div>
								</div>
								<div class="boardPickerInner">
									<ul>
										<c:forEach items="${obList }" var="v">
											<li class="boardPickerItem" data-id="${v.id }" data-privacy="public" data-collaborative="">
												<div class="iconWrapper">
													<span class="secretIcon hidden"></span>
													<span class="collaborativeIcon hidden"></span>
												</div> ${v.boardName }
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="boardPickerOuter">
								<div class="currentBoard" data-id="${oc.boardId }" data-privacy="public" data-collaborative="">
									<div class="iconWrapper">
										<span class="secretIcon hidden"></span> 
										<span class="collaborativeIcon hidden"></span> 
										<span class="downArrow"></span>
									</div>
									<span class="currentBoardName"></span>
								</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<h3>
						<label for="pinFormDescription">Description</label>
					</h3>
					<div>
						<div class="ui-TextField ajax Module">
							<textarea id="pinFormDescription" name="description" class="content" placeholder="Add a short description to your pin">Cool photography tricks!</textarea>
						</div>
					</div>
				</li>
			</ul>
			<div class="formFooter">
				<div class="formFooterButtons">
					<button type="submit" class="rounded Button repinSmall pinIt primary Module ajax btn">
						<em></em> <span class="accessibilityText">Pin it</span>
					</button>
					<button type="button" class="rounded Button hasText Module ajax btn cancelButton">
						<span class="buttonText">Cancel</span>
					</button>
				</div>
			</div>
			<input type="hidden" name="link" id="pinFormLink" value="http://improvephotography.com/8806/photography-tricks/">
		</form>
	</div>
</div>