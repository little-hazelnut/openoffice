package com.xiaozhen.xmlHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.styles.Alignment;
import com.xiaozhen.styles.Border;
import com.xiaozhen.styles.CellXf;
import com.xiaozhen.styles.ColorStyle;
import com.xiaozhen.styles.Fill;
import com.xiaozhen.styles.Font;
import com.xiaozhen.styles.GradientFillEffects;
import com.xiaozhen.styles.Stop;
import com.xiaozhen.styles.Styles;
import com.xiaozhen.styles.Alignment.Horizontal;
import com.xiaozhen.styles.Alignment.ReadingOrder;
import com.xiaozhen.styles.Alignment.Vertical;
import com.xiaozhen.styles.Border.BorderStyle;
import com.xiaozhen.styles.Fill.PatternType;
import com.xiaozhen.styles.Font.Font_u;
import com.xiaozhen.styles.Font.VertAlign;
import com.xiaozhen.styles.GradientFillEffects.GradientType;
import com.xiaozhen.util.SheetTools;

public class StylesHandler extends DefaultHandler {

	private Styles styles = Styles.getInstance();
	String attriValue;
	// numFmts
	private Map<Integer, String> numFmts;
	// fonts
	private List<Font> fonts;
	private Font font;
	// fills
	private List<Fill> fills;
	private Fill fill;
	private ColorStyle colorStyle;
	private GradientFillEffects gFillEffects;
	private Stop stop;
	// borders
	private List<Border> borders;
	private Border border;
	private BorderStyle bstyle;
	// cellxfs
	private List<CellXf> cellXfs;
	private CellXf cellXf;
	private Alignment alignment;

	private boolean startStopTag = false;
	private boolean startFontTag = false;
	private boolean startBordersTag = false;
	private boolean startCellXfsTag = false;
	private boolean applyAlignment = false;

	// numFmts
	private final String TAG_numFmts = "numFmts";
	private final String TAG_numFmt = "numFmt";
	private final String QNAME_formatCode = "formatCode";
	private final String QNAME_numFmtId = "numFmtId";
	// fonts
	private final String TAG_fonts = "fonts";
	private final String TAG_font = "font";
	private final String TAG_sz = "sz";
	private final String TAG_color = "color";
	private final String TAG_name = "name";
	private final String TAG_family = "family";
	private final String TAG_charset = "charset";
	private final String TAG_scheme = "scheme";
	private final String TAG_b = "b";
	private final String TAG_i = "i";
	private final String TAG_strike = "strike";
	private final String TAG_u = "u";
	private final String TAG_vertAlign = "vertAlign";
	private final String QNAME_val = "val";
	private final String QVAL_double = "double";
	private final String QVAL_singleAccounting = "singleAccounting";
	private final String QVAL_doubleAccounting = "doubleAccounting";
	private final String QVAL_superscript = "superscript";
	// colorStyle
	private final String QNAME_theme = "theme";
	private final String QNAME_tint = "tint";
	private final String QNAME_rgb = "rgb";
	private final String QNAME_indexed = "indexed";
	private final String QNAME_auto = "auto";
	// fills
	private final String TAG_fills = "fills";
	private final String TAG_fill = "fill";
	private final String TAG_patternFill = "patternFill";
	private final String TAG_fgColor = "fgColor";
	private final String TAG_bgColor = "bgColor";
	private final String TAG_gradientFill = "gradientFill";
	private final String TAG_stop = "stop";
	private final String QNAME_patternType = "patternType";
	private final String QNAME_GradientType = "type";
	private final String QNAME_top = "top";
	private final String QNAME_bottom = "bottom";
	private final String QNAME_left = "left";
	private final String QNAME_right = "right";
	private final String QNAME_degree = "degree";
	private final String QNAME_position = "position";
	private final String QVAL_gray125 = "gray125";
	private final String QVAL_darkGray = "darkGray";
	private final String QVAL_mediumGray = "mediumGray";
	private final String QVAL_lightGray = "lightGray";
	private final String QVAL_gray0625 = "gray0625";
	private final String QVAL_darkHorizontal = "darkHorizontal";
	private final String QVAL_darkVertical = "darkVertical";
	private final String QVAL_darkDown = "darkDown";
	private final String QVAL_darkUp = "darkUp";
	private final String QVAL_darkGrid = "darkGrid";
	private final String QVAL_darkTrellis = "darkTrellis";
	private final String QVAL_lightHorizontal = "lightHorizontal";
	private final String QVAL_lightVertical = "lightVertical";
	private final String QVAL_lightDown = "lightDown";
	private final String QVAL_lightUp = "lightUp";
	private final String QVAL_lightGrid = "lightGrid";
	private final String QVAL_lightTrellis = "lightTrellis";
	private final String QVAL_solid = "solid";
	private final String QVAL_none = "none";
	private final String QVAL_path = "path";
	private final String QVAL_linear = "linear";
	// borders
	private final String TAG_borders = "borders";
	private final String TAG_border = "border";
	private final String TAG_left = "left";
	private final String TAG_right = "right";
	private final String TAG_top = "top";
	private final String TAG_bottom = "bottom";
	private final String TAG_diagonal = "diagonal";
	private final String QNAME_diagonalUp = "diagonalUp";
	private final String QNAME_diagonalDown = "diagonalDown";
	private final String QNAME_style = "style";
	private final String QVAL_hair = "hair";
	private final String QVAL_slantDashDot = "slantDashDot";
	private final String QVAL_dotted = "dotted";
	private final String QVAL_mediumDashDot = "mediumDashDot";
	private final String QVAL_dashDotDot = "dashDotDot";
	private final String QVAL_doubleL = "double";
	private final String QVAL_medium = "medium";
	private final String QVAL_mediumDashed = "mediumDashed";
	private final String QVAL_dashDot = "dashDot";
	private final String QVAL_thin = "thin";
	private final String QVAL_dashed = "dashed";
	private final String QVAL_thick = "thick";
	private final String QVAL_mediumDashDotDot = "mediumDashDotDot";
	// cellXfs
	private final String TAG_cellXfs = "cellXfs";
	private final String TAG_xf = "xf";
	private final String TAG_alignment = "alignment";
	private final String QNAME_borderId = "borderId";
	private final String QNAME_fillId = "fillId";
	private final String QNAME_fontId = "fontId";
	private final String QNAME_applyAlignment = "applyAlignment";
	private final String QNAME_applyBorder = "applyBorder";
	private final String QNAME_applyFont = "applyFont";
	private final String QNAME_applyFill = "applyFill";
	private final String QNAME_applyNumberFormat = "applyNumberFormat";
	private final String QNAME_vertical = "vertical";
	private final String QNAME_horizontal = "horizontal";
	private final String QNAME_readingOrder = "readingOrder";
	private final String QNAME_shrinkToFit = "shrinkToFit";
	private final String QNAME_textRotation = "textRotation";
	private final String QNAME_wrapText = "wrapText";
	private final String QNAME_indent = "indent";
	private final String QNAME_justifyLastLine = "justifyLastLine";
	// alignment->vertical、horizontal
	private final String QVAL_center = "center";
	private final String QVAL_justify = "justify";
	private final String QVAL_distributed = "distributed";
	// alignment->vertical
	private final String QVAL_top = "top";
	private final String QVAL_bottom = "bottom";
	// alignment-> horizontal
	private final String QVAL_left = "left";
	private final String QVAL_right = "right";
	private final String QVAL_fill = "fill";
	private final String QVAL_centerContinuous = "centerContinuous";
	// alignment->readingOrder
	private final String QVAL_roltr = "1"; // left to right
	private final String QVAL_rortl = "2"; // right to left

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals(TAG_numFmts)) {// numFmts
			styles.setNumFmts(numFmts);
			numFmts = null;
		} else if (qName.equals(TAG_font)) {// font
			fonts.add(font);
			font = null;
			startFontTag = false;
		} else if (qName.equals(TAG_fonts)) {// fonts
			styles.setFonts(fonts);
			fonts = null;
		} else if (qName.equals(TAG_bgColor)) {// bgColor
			fill.setBgColor(colorStyle);
			colorStyle = null;
		} else if (qName.equals(TAG_fgColor)) {// fgColor
			fill.setFgColor(colorStyle);
			colorStyle = null;
		} else if (qName.equals(TAG_stop)) {// stop
			gFillEffects.addStops(stop);
			stop = null;
			startStopTag = false;
		} else if (qName.equals(TAG_gradientFill)) {// gradientFill
			fill.setGFEffects(gFillEffects);
			gFillEffects = null;
		} else if (qName.equals(TAG_fill)) {// fill
			fills.add(fill);
			fill = null;
		} else if (qName.equals(TAG_fills)) {// fills
			styles.setFills(fills);
			fills = null;
		} else if (qName.equals(TAG_left)) {// border->left
			if (bstyle != null) {
				border.setbLeftStyle(bstyle);
			}
			if (colorStyle != null) {
				border.setbLeftColor(colorStyle);
			}
			bstyle = null;
			colorStyle = null;
		} else if (qName.equals(TAG_right)) {// border->right
			if (bstyle != null) {
				border.setbRightStyle(bstyle);
			}
			if (colorStyle != null) {
				border.setbRightColor(colorStyle);
			}
			bstyle = null;
			colorStyle = null;
		} else if (qName.equals(TAG_bottom)) {// border->bottom
			if (bstyle != null) {
				border.setbBottomStyle(bstyle);
			}
			if (colorStyle != null) {
				border.setbBottomColor(colorStyle);
			}
			bstyle = null;
			colorStyle = null;
		} else if (qName.equals(TAG_top)) {// border->top
			if (bstyle != null) {
				border.setbTopStyle(bstyle);
			}
			if (colorStyle != null) {
				border.setbTopColor(colorStyle);
			}
			bstyle = null;
			colorStyle = null;
		} else if (qName.equals(TAG_diagonal)) {// border->diagonal
			if (bstyle != null) {
				border.setbDiagonalStyle(bstyle);
			}
			if (colorStyle != null) {
				border.setbDiagonalColor(colorStyle);
			}
			bstyle = null;
			colorStyle = null;
		} else if (qName.equals(TAG_border)) {// border
			borders.add(border);
			border = null;
		} else if (qName.equals(TAG_borders)) {// borders
			styles.setBorders(borders);
			borders = null;
			startBordersTag = false;
		} else if (startCellXfsTag && qName.equals(TAG_xf)) {// cellxfs->xf
			cellXf.setAlignment(alignment);
			alignment = null;
			applyAlignment = false;
			cellXfs.add(cellXf);
			cellXf = null;
		} else if (qName.equals(TAG_cellXfs)) {// cellxfs
			styles.setCellxfs(cellXfs);
			cellXfs = null;
			startCellXfsTag = false;
		}
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(TAG_numFmts)) {// 解析numFmts标签
			numFmts = new HashMap<Integer, String>();
		} else if (qName.equals(TAG_numFmt)) {// 解析numFmt标签
			numFmts.put(Integer.parseInt(attributes.getValue(QNAME_numFmtId)),
					attributes.getValue(QNAME_formatCode));
		} else if (qName.equals(TAG_fonts)) {// fonts
			fonts = new ArrayList<Font>();
		} else if (qName.equals(TAG_font)) {// font
			font = new Font();
			startFontTag = true;
		} else if (qName.equals(TAG_sz)) {// size
			font.setSize(Byte.parseByte(attributes.getValue(QNAME_val)));
		} else if (startFontTag && qName.equals(TAG_color)) {// font->color
			colorStyle = parseColorStyle(attributes);
			font.setColor(colorStyle);
			colorStyle = null;
		} else if (qName.equals(TAG_name)) {// name
			font.setName(attributes.getValue(QNAME_val));
		} else if (qName.equals(TAG_family)) {// family
			font.setFamily(Short.parseShort(attributes.getValue(QNAME_val)));
		} else if (qName.equals(TAG_charset)) {// charset
			font.setCharset(Short.parseShort(attributes.getValue(QNAME_val)));
		} else if (qName.equals(TAG_scheme)) {// scheme
			font.setScheme(attributes.getValue(QNAME_val));
		} else if (qName.equals(TAG_b)) {// bold
			font.setBold(true);
		} else if (qName.equals(TAG_i)) {// incline
			font.setIncline(true);
		} else if (qName.equals(TAG_strike)) {// strike
			font.setStrike(true);
		} else if (qName.equals(TAG_u)) {// underline
			if ((attriValue = attributes.getValue(QNAME_val)) != null) {
				if (attriValue.equals(QVAL_double)) {
					font.setU(Font_u.doubleU);
				} else if (attriValue.equals(QVAL_singleAccounting)) {
					font.setU(Font_u.singleAccounting);
				} else if (attributes.equals(QVAL_doubleAccounting)) {
					font.setU(Font_u.doubleAccounting);
				}
			} else {
				font.setU(Font_u.singleU);
			}
		} else if (qName.equals(TAG_vertAlign)) {// vertAlign
			attriValue = attributes.getValue(QNAME_val);
			if (attriValue.equals(QVAL_superscript)) {
				font.setvAlign(VertAlign.Superscript);
			} else {
				font.setvAlign(VertAlign.Subscript);
			}
		} else if (qName.equals(TAG_fills)) {// fills
			fills = new ArrayList<Fill>();
		} else if (qName.equals(TAG_fill)) {// fill
			fill = new Fill();
		} else if (qName.equals(TAG_patternFill)) {// patternFill
			attriValue = attributes.getValue(QNAME_patternType);
			if (attriValue.equals(QVAL_none)) {
				fill.setPatternType(PatternType.none);
			} else if (attriValue.equals(QVAL_gray125)) {
				fill.setPatternType(PatternType.gray125);
			} else if (attriValue.equals(QVAL_darkGray)) {
				fill.setPatternType(PatternType.darkGray);
			} else if (attriValue.equals(QVAL_mediumGray)) {
				fill.setPatternType(PatternType.mediumGray);
			} else if (attriValue.equals(QVAL_lightGray)) {
				fill.setPatternType(PatternType.lightGray);
			} else if (attriValue.equals(QVAL_gray0625)) {
				fill.setPatternType(PatternType.gray0625);
			} else if (attriValue.equals(QVAL_darkHorizontal)) {
				fill.setPatternType(PatternType.darkHorizontal);
			} else if (attriValue.equals(QVAL_darkVertical)) {
				fill.setPatternType(PatternType.darkVertical);
			} else if (attriValue.equals(QVAL_darkDown)) {
				fill.setPatternType(PatternType.darkDown);
			} else if (attriValue.equals(QVAL_darkUp)) {
				fill.setPatternType(PatternType.darkUp);
			} else if (attriValue.equals(QVAL_darkGrid)) {
				fill.setPatternType(PatternType.darkGrid);
			} else if (attriValue.equals(QVAL_darkTrellis)) {
				fill.setPatternType(PatternType.darkTrellis);
			} else if (attriValue.equals(QVAL_lightHorizontal)) {
				fill.setPatternType(PatternType.lightHorizontal);
			} else if (attriValue.equals(QVAL_lightVertical)) {
				fill.setPatternType(PatternType.lightVertical);
			} else if (attriValue.equals(QVAL_lightDown)) {
				fill.setPatternType(PatternType.lightDown);
			} else if (attriValue.equals(QVAL_lightUp)) {
				fill.setPatternType(PatternType.lightUp);
			} else if (attriValue.equals(QVAL_lightGrid)) {
				fill.setPatternType(PatternType.lightGrid);
			} else if (attriValue.equals(QVAL_lightTrellis)) {
				fill.setPatternType(PatternType.lightTrellis);
			} else if (attriValue.equals(QVAL_solid)) {
				fill.setPatternType(PatternType.solid);
			}
		} else if (qName.equals(TAG_fgColor) || qName.equals(TAG_bgColor)) {// fgColor,bgColor
			colorStyle = parseColorStyle(attributes);
		} else if (qName.equals(TAG_gradientFill)) {// gradientFill
			gFillEffects = new GradientFillEffects();
			if ((attriValue = attributes.getValue(QNAME_GradientType)) != null) {// gradientType
				float top = Float.parseFloat(attributes.getValue(QNAME_top));
				float bottom = Float.parseFloat(attributes
						.getValue(QNAME_bottom));
				float left = Float.parseFloat(attributes.getValue(QNAME_left));
				float right = Float
						.parseFloat(attributes.getValue(QNAME_right));
				if (attriValue.equals(QVAL_path)) {
					gFillEffects.setGradientType(GradientType.path, top,
							bottom, left, right);
				} else if (attriValue.equals(QVAL_linear)) {
					gFillEffects.setGradientType(GradientType.linear, top,
							bottom, left, right);
				}
			} else {
				if ((attriValue = attributes.getValue(QNAME_degree)) != null) {// degree
					gFillEffects.setDegree(Short.parseShort(attriValue));
				}
			}
		} else if (qName.equals(TAG_stop)) {// stop
			startStopTag = true;
			stop = new Stop();
			float position = Float.parseFloat(attributes
					.getValue(QNAME_position));
			stop.setPosition(position);
		} else if (startStopTag && qName.equals(TAG_color)) {// stop->color
			colorStyle = parseColorStyle(attributes);
			stop.setColorStyle(colorStyle);
			colorStyle = null;
		} else if (qName.equals(TAG_borders)) {// borders
			borders = new ArrayList<Border>();
			startBordersTag = true;
		} else if (qName.equals(TAG_border)) {// border
			border = new Border();
			if (attributes.getValue(QNAME_diagonalUp) != null) {
				border.setDiagonalUp(true);
			}
			if (attributes.getValue(QNAME_diagonalDown) != null) {
				border.setDiagonalDown(true);
			}
		} else if (qName.equals(TAG_left) || qName.equals(TAG_right)
				|| qName.equals(TAG_top) || qName.equals(TAG_bottom)
				|| qName.equals(TAG_diagonal)) {// border->
												// left、right、top、bottom、diagonal
			if ((attriValue = attributes.getValue(QNAME_style)) != null) {
				if (attriValue.equals(QVAL_hair)) {
					bstyle = BorderStyle.hair;
				} else if (attriValue.equals(QVAL_slantDashDot)) {
					bstyle = BorderStyle.slantDashDot;
				} else if (attriValue.equals(QVAL_dotted)) {
					bstyle = BorderStyle.dotted;
				} else if (attriValue.equals(QVAL_mediumDashDot)) {
					bstyle = BorderStyle.mediumDashDot;
				} else if (attriValue.equals(QVAL_dashDotDot)) {
					bstyle = BorderStyle.dashDotDot;
				} else if (attriValue.equals(QVAL_doubleL)) {
					bstyle = BorderStyle.doubleL;
				} else if (attriValue.equals(QVAL_medium)) {
					bstyle = BorderStyle.medium;
				} else if (attriValue.equals(QVAL_mediumDashed)) {
					bstyle = BorderStyle.mediumDashed;
				} else if (attriValue.equals(QVAL_dashDot)) {
					bstyle = BorderStyle.dashDot;
				} else if (attriValue.equals(QVAL_thin)) {
					bstyle = BorderStyle.thin;
				} else if (attriValue.equals(QVAL_dashed)) {
					bstyle = BorderStyle.dashed;
				} else if (attriValue.equals(QVAL_thick)) {
					bstyle = BorderStyle.thick;
				} else if (attriValue.equals(QVAL_mediumDashDotDot)) {
					bstyle = BorderStyle.mediumDashDotDot;
				}
			}
		} else if (startBordersTag && qName.equals(TAG_color)) {// border->color
			colorStyle = parseColorStyle(attributes);
		} else if (qName.equals(TAG_cellXfs)) {// cellXfs
			cellXfs = new ArrayList<CellXf>();
			startCellXfsTag = true;
		} else if (startCellXfsTag && qName.equals(TAG_xf)) {// cellXfs->xf
			cellXf = new CellXf();
			alignment = new Alignment();
			if (attributes.getIndex(QNAME_applyNumberFormat) != -1) {// applyNumberFormat
				cellXf.setNumFmtId(Integer.parseInt(attributes
						.getValue(QNAME_numFmtId)));
			}
			if (attributes.getIndex(QNAME_applyBorder) != -1) {// applyBorder
				cellXf.setBorderId(Integer.parseInt(attributes
						.getValue(QNAME_borderId)));
			}
			if (attributes.getIndex(QNAME_applyFill) != -1) {// applyFill
				cellXf.setFillId(Integer.parseInt(attributes
						.getValue(QNAME_fillId)));
			}
			if (attributes.getIndex(QNAME_applyFont) != -1) {// applyFont
				cellXf.setFontId(Integer.parseInt(attributes
						.getValue(QNAME_fontId)));
			}
			if (attributes.getIndex(QNAME_applyAlignment) != -1) {// applyAlignment
				applyAlignment = true;
			}
		} else if (startCellXfsTag && qName.equals(TAG_alignment)) {// cellXf->alignment
			if (applyAlignment == true) {
				if ((attriValue = attributes.getValue(QNAME_vertical)) != null) {// vertical
					if (attriValue.equals(QVAL_center)) {
						alignment.setVertical(Vertical.center);
					} else if (attriValue.equals(QVAL_justify)) {
						alignment.setVertical(Vertical.justify);
					} else if (attriValue.equals(QVAL_distributed)) {
						alignment.setVertical(Vertical.distributed);
					} else if (attriValue.equals(QVAL_top)) {
						alignment.setVertical(Vertical.top);
					} else if (attriValue.equals(QVAL_bottom)) {
						alignment.setVertical(Vertical.bottom);
					}
				}
				if ((attriValue = attributes.getValue(QNAME_horizontal)) != null) {// horizontal
					if (attriValue.equals(QVAL_center)) {
						alignment.setHorizontal(Horizontal.center);
					} else if (attriValue.equals(QVAL_justify)) {
						alignment.setHorizontal(Horizontal.justify);
					} else if (attriValue.equals(QVAL_distributed)) {
						alignment.setHorizontal(Horizontal.distributed);
					} else if (attriValue.equals(QVAL_left)) {
						alignment.setHorizontal(Horizontal.left);
					} else if (attriValue.equals(QVAL_right)) {
						alignment.setHorizontal(Horizontal.right);
					} else if (attriValue.equals(QVAL_fill)) {
						alignment.setHorizontal(Horizontal.fill);
					} else if (attriValue.equals(QVAL_centerContinuous)) {
						alignment.setHorizontal(Horizontal.centerContinuous);
					}
				}
				if (attributes.getValue(QNAME_wrapText) != null) {// wrapText
					alignment.setWrapText(true);
				}
				if (attributes.getValue(QNAME_shrinkToFit) != null) {// shrinkToFit
					alignment.setShrinkToFit(true);
				}
				if ((attriValue = attributes.getValue(QNAME_readingOrder)) != null) {// readingOrder
					if (attriValue.equals(QVAL_roltr)) {// left to right
						alignment.setReadingOrder(ReadingOrder.leftToRight);
					} else if (attriValue.equals(QVAL_rortl)) {// right to left
						alignment.setReadingOrder(ReadingOrder.RightToLeft);
					}
				}
				if ((attriValue = attributes.getValue(QNAME_textRotation)) != null) {// textRotation
					alignment.setTextRotation(Byte.parseByte(attriValue));
				}
				if ((attriValue = attributes.getValue(QNAME_indent)) != null) {// indent
					alignment.setIndent(Byte.parseByte(attriValue));
				}
				if (attributes.getValue(QNAME_justifyLastLine) != null) {// justifyLastLine
					alignment.setJustifyLastLine(true);
				}
			}
		}
	}

	private ColorStyle parseColorStyle(Attributes attributes) {// parse colorStyle Tag														
		ColorStyle colorStyle = new ColorStyle();
		if ((attriValue = attributes.getValue(QNAME_theme)) != null) {// theme
			colorStyle.setTheme(Byte.parseByte(attriValue));
			if ((attriValue = attributes.getValue(QNAME_tint)) != null) {// tint
				colorStyle.setTint(Double.parseDouble(attriValue));
			}
		} else if ((attriValue = attributes.getValue(QNAME_rgb)) != null) {// rgb
			colorStyle.setRgb(SheetTools.rgbStringToInt(attriValue));
		} else if ((attriValue = attributes.getValue(QNAME_indexed)) != null) {// indexed
			colorStyle.setIndexed(Byte.parseByte(attriValue));
		} else if (attributes.getValue(QNAME_auto) != null) {// auto
			colorStyle.setTheme((byte) 1);
		}
		return colorStyle;
	}
}
